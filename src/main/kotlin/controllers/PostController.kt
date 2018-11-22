package controllers

import io.javalin.Context
import services.PostService

class PostController(private val postSertvice : PostService) {

    fun getPosts(context: Context) {
        val posts = postSertvice.readAll()
        context.json(posts)
    }

//    fun createPost(context: Context){
//        val post = context.body<Post>()
//        posts.add(post)
//    }
}
