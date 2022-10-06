package com.thiagosena.coroutines.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestWebExceptionHandler {

    @ExceptionHandler(PostNotFoundException::class)
    suspend fun handler(ex: PostNotFoundException): ResponseEntity<ErrorMessageModel> {
        println("error $ex")
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

}

data class ErrorMessageModel(val status: Int, val message: String? = null)
