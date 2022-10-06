package com.thiagosena.coroutines.services.impl

import com.thiagosena.coroutines.entities.Comment
import com.thiagosena.coroutines.repositories.CommentRepository
import com.thiagosena.coroutines.services.CommentService
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository
) : CommentService {

    override suspend fun save(comment: Comment): Comment = commentRepository.save(comment)

    override suspend fun countByPostId(postId: String): Long = commentRepository.countByPostId(postId)

    override fun findByPostId(postId: String): Flow<Comment> = commentRepository.findByPostId(postId)
}
