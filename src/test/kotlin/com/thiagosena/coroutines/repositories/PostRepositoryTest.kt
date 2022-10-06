package com.thiagosena.coroutines.repositories

import com.mongodb.assertions.Assertions.assertNotNull
import com.thiagosena.coroutines.config.MongoSpringBootTest
import com.thiagosena.coroutines.entities.Post
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations
import org.springframework.data.mongodb.core.insert


@MongoSpringBootTest
class PostRepositoryTest {

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var mongo: ReactiveFluentMongoOperations

    @AfterEach
    fun cleanUp() {
        runBlocking {
            postRepository.deleteAll()
        }
    }

    @Test
    fun `get all posts`() {

        val inserted: Post? = mongo.insert<Post>()
            .one(Post(title = "title", content = "content"))
            .block()

        assertNotNull(inserted?.id)

        runBlocking {
            val post = postRepository.findOne(inserted?.id!!)
            assertEquals("title", post?.title)
            assertEquals("content", post?.content)
        }

    }
}
