package com.thiagosena.coroutines.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Comment(
    @Id
    val id: String? = null,
    val content: String,
    val postId: String
)
