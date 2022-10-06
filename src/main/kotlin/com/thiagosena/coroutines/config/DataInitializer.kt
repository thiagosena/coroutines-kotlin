package com.thiagosena.coroutines.config

import com.thiagosena.coroutines.repositories.PostRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val postRepository: PostRepository) {

    @EventListener(value = [ApplicationReadyEvent::class])
    fun init() {
        println("Start data initialization")

        runBlocking {
            val deleted = postRepository.deleteAll()
            println("$deleted posts removed")
            postRepository.init()
        }

        print("done data initialization")
    }
}
