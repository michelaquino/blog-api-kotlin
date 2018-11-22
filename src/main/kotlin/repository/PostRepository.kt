package repository

import domain.Post

class PostRepository : interfaces.PostRepository {
    override fun readAll(): List<Post> {
        return arrayListOf(
                Post(id = 1, title = "Title 1", content = "Description 1"),
                Post(id = 2, title = "Title 2", content = "Description 2"),
                Post(id = 3, title = "Title 3", content = "Description 3")
        )
    }
}