package com.thiagosena.coroutines.controllers

import com.thiagosena.coroutines.entities.Post
import com.thiagosena.coroutines.services.PostService
import kotlinx.coroutines.reactive.asFlow
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@WebFluxTest(
    controllers = [PostController::class],
    excludeAutoConfiguration = [
        ReactiveUserDetailsServiceAutoConfiguration::class,
        ReactiveSecurityAutoConfiguration::class
    ]
)
class PostControllerTest {

    @MockBean
    private lateinit var postService: PostService

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun `get all posts`() {
        val postsFlow = Flux.just("Post one", "Post two")
            .map {
                Post(title = it, content = "content of $it")
            }
            .asFlow()
        given(postService.findAll()).willReturn(postsFlow)
        client.get()
            .uri("/api/posts")
            .exchange()
            .expectStatus()
            .isOk

        verify(postService, times(1)).findAll()
        verifyNoMoreInteractions(postService)

    }
}
