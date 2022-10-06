package com.thiagosena.coroutines.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Post(
    @Id
    val id: String? = null,
    val title: String,
    val content: String
)
