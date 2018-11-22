import controllers.PostController
import interfaces.PostRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import services.PostService

object Application : KoinComponent {
    fun init() {
        val postController: PostController by inject()

        val koinModules = module {
            single { repository.PostRepository() as PostRepository}
            single { PostService(get()) }
            single { PostController(get()) }
        }

        StandAloneContext.startKoin(listOf(koinModules))

        val app = Javalin
                .create()
                .enableCorsForOrigin("*")
                .start(8080)

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
}