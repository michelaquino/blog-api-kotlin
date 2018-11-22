package interfaces

import domain.Post

interface PostRepository {
    fun readAll() : List<Post>
}