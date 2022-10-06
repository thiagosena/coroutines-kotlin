package com.thiagosena.coroutines.repositories

import com.thiagosena.coroutines.entities.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations
import org.springframework.data.mongodb.core.flow
import org.springframework.data.mongodb.core.insert
import org.springframework.data.mongodb.core.oneAndAwait
import org.springframework.data.mongodb.core.query
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class CommentRepository(private val mongo: ReactiveFluentMongoOperations) {

    suspend fun save(comment: Comment): Comment = mongo.insert<Comment>().oneAndAwait(comment)

    suspend fun countByPostId(postId: String): Long = mongo.query<Comment>()
        .matching(query(where("postId").isEqualTo(postId)))
        .count()
        .awaitSingle()

    fun findByPostId(postId: String): Flow<Comment> = mongo.query<Comment>()
        .matching(query(where("postId").isEqualTo(postId))).flow()
}
