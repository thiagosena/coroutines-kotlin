package com.thiagosena.coroutines

import com.thiagosena.coroutines.config.MongoSpringBootTest
import com.thiagosena.coroutines.controllers.PostController
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@MongoSpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = [CoroutinesKotlinApplication::class])
class PostApplicationTest {

    @Autowired
    private lateinit var postController: PostController

    private lateinit var client: WebTestClient

    @BeforeAll
    fun setup() {
        client = WebTestClient.bindToController(postController).build()
    }

    @Test
    fun `get all posts`() {
        client.get().uri("/api/posts")
            .exchange().expectStatus().isOk
    }

}
