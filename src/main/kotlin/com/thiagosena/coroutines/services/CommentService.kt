package com.thiagosena.coroutines.services

import com.thiagosena.coroutines.entities.Comment
import kotlinx.coroutines.flow.Flow

interface CommentService {

    suspend fun save(comment: Comment): Comment

    suspend fun countByPostId(postId: String): Long

    fun findByPostId(postId: String): Flow<Comment>
}
