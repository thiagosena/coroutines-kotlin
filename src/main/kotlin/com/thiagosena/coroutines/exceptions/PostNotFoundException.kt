package com.thiagosena.coroutines.exceptions

class PostNotFoundException(postId: String) : RuntimeException("Post:$postId is not fount!")
