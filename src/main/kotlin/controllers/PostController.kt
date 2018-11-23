package controllers

import domain.Post
import io.javalin.Context
import services.PostService

class PostController(private val postService : PostService) {

    fun getPosts(context: Context) {
        val posts = postService.readAll()
        context.json(posts)
    }

    fun createPost(context: Context){
        val post = context.body<Post>()

        postService.create(post)
    }
}
