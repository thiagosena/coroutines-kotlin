package com.thiagosena.coroutines.controllers

import com.thiagosena.coroutines.entities.Comment
import com.thiagosena.coroutines.services.CommentService
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
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/post/{postId}")
    fun getAllByPostId(@PathVariable postId: String): Flow<Comment> = commentService.findByPostId(postId)

    @GetMapping("/post/{postId}/count")
    suspend fun countByPostId(@PathVariable postId: String): Long = commentService.countByPostId(postId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun save(@RequestBody comment: Comment) = commentService.save(comment)
}
