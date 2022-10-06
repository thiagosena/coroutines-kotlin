package com.thiagosena.coroutines.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Post(
    @Id
    private val id: String? = null,
    private val title: String,
    private val content: String
)
