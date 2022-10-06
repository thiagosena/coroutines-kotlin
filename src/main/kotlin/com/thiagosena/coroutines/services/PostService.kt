package com.thiagosena.coroutines.services

import com.thiagosena.coroutines.entities.Post
import kotlinx.coroutines.flow.Flow

interface PostService {
    suspend fun count(): Long
    fun findAll(): Flow<Post>
    suspend fun findOne(id: String): Post?
    suspend fun deleteAll(): Long
    suspend fun save(post: Post): Post
    suspend fun update(post: Post): Post
}