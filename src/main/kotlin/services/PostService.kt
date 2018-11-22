package services

import domain.Post

class PostService(private val postRepository: interfaces.PostRepository) {
    fun readAll() : List<Post> {
        return postRepository.readAll()
    }
}