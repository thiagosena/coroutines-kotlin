package com.thiagosena.coroutines.services.impl

import com.thiagosena.coroutines.entities.Post
import com.thiagosena.coroutines.repositories.PostRepository
import com.thiagosena.coroutines.services.PostService
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {

    override suspend fun count(): Long = postRepository.count()

    override fun findAll(): Flow<Post> = postRepository.findAll()

    override suspend fun findOne(id: String): Post? = postRepository.findOne(id)

    override suspend fun deleteAll(): Long = postRepository.deleteAll()

    override suspend fun save(post: Post): Post = postRepository.save(post)

    override suspend fun update(post: Post): Post = postRepository.update(post)
}