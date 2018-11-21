import controllers.PostController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main(args: Array<String>) {
    val app = Javalin.create()
            .enableCorsForOrigin("*")
            .start(8080)

    app.get("/") {
        ctx -> ctx.result("Hello World")
    }

    app.routes {
        path("posts") {
            get(PostController::getPosts)
            post(PostController::createPost)
        }
    }
}
