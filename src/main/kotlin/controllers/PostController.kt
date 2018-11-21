package controllers

import io.javalin.Context
import java.util.*

object PostController {

    private data class Post(val id: String = randomId(), val title: String, val description: String)

    private val posts = arrayListOf(
            Post(id = randomId(), title = "Title 1", description = "Description 1"),
            Post(id = randomId(), title = "Title 2", description = "Description 2"),
            Post(id = randomId(), title = "Title 3", description = "Description 3")
    )

    fun getPosts(context: Context) {
        context.json(posts)
    }

    fun createPost(context: Context){
        val post = context.body<Post>()
        posts.add(post)
    }

    private fun randomId() = UUID.randomUUID().toString()
}
