package com.thiagosena.coroutines.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Comment(
    @Id
    private val id: String? = null,
    private val content: String,
    private val postId: String
)