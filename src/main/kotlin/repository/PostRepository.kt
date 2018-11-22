package repository

import domain.Post
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository : interfaces.PostRepository {
    override fun readAll(): List<Post> {
        var posts = listOf<Post>()

        transaction {
            posts = PostsTable
                    .selectAll()
                    .map { post ->
                        Post(id = post[PostsTable.id], title = post[PostsTable.title], content = post[PostsTable.content])
                    }

        }

        return posts
    }
}

object PostsTable : Table("posts") {
    val id = integer("id")
    val title = varchar("title", 500)
    val content = text("content")
}