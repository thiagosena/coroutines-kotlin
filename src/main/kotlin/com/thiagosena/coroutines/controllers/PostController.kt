package com.thiagosena.coroutines.controllers

import com.thiagosena.coroutines.entities.Post
import com.thiagosena.coroutines.services.PostService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService
) {

    @GetMapping
    fun findAll(): Flow<Post> = postService.findAll()

    @GetMapping("count")
    suspend fun count(): Long = postService.count()

    @GetMapping("{id}")
    suspend fun findOne(@PathVariable id: String): Post = postService.findOne(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun save(@RequestBody post: Post) = postService.save(post)

}
