import controllers.PostController
import interfaces.PostRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import services.PostService

object Application : KoinComponent {
    fun init() {
        configureDependenciInjection()
        configureDatabase()

        val app = Javalin
                .create()
                .enableCorsForOrigin("*")
                .start(8080)

        configureRoutes(app)
    }

    private fun configureDependenciInjection() {
        val koinModules = module {
            single { repository.PostRepository() as PostRepository}
            single { PostService(get()) }
            single { PostController(get()) }
        }

        StandAloneContext.startKoin(listOf(koinModules))
    }

    private fun configureRoutes(app: Javalin) {
        val postController: PostController by inject()

        app.get("/") {
            ctx -> ctx.result("Hello World")
        }

        app.routes {
            ApiBuilder.path("posts") {
                ApiBuilder.get { context -> postController.getPosts(context) }
//            post(PostController::createPost)
            }
        }
    }

    private fun configureDatabase() {
        Database.connect("jdbc:postgresql://localhost:5432/blogpost", "org.postgresql.Driver", "user",password = "password")
    }
}