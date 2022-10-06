package com.thiagosena.coroutines.exceptions

class PostNotFoundException(postId: String) : RuntimeException("Post with id=$postId is not found!")
