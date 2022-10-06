package com.thiagosena.coroutines

import com.thiagosena.coroutines.config.MongoSpringBootTest
import com.thiagosena.coroutines.entities.Post
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.kotlin.test.test


@TestInstance(Lifecycle.PER_CLASS)
@MongoSpringBootTest
@SpringBootTest(
    classes = [CoroutinesKotlinApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class PostIntegrationTest {

    private lateinit var client: WebClient

    @LocalServerPort
    private var port: Int = 9478

    @BeforeAll
    fun setup() {
        client = WebClient.create("http://localhost:$port")
    }

    @Test
    fun `get all posts`() {
        client.get()
            .uri("/api/posts")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(Post::class.java)
//            .exchangeToMono { response ->
//                ServerResponse
//                    .status(response.statusCode())
//                    .headers { it.addAll(response.headers().asHttpHeaders()) }
//                    .body(response.bodyToMono<ByteArray>())
//            }
//            .exchangeToMono { response ->
//                response.bodyToMono<ByteArray>()
//                    .defaultIfEmpty(ByteArray(0))
//                    .flatMap { body ->
//                        ServerResponse
//                            .status(response.statusCode())
//                            .headers { it.addAll(response.headers().asHttpHeaders()) }
//                            .bodyValue(body)
//                    }
//            }
            .test()
//            .expectNextMatches { it.statusCode() == HttpStatus.OK }
            .expectNextMatches {
                it.title == "My first post title" && it.content == "Content of my first post"
            }
            .expectNextMatches {
                it.title == "My second post title" && it.content == "Content of my second post"
            }
            .verifyComplete()

    }
}
