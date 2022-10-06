package com.thiagosena.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinesKotlinApplication

fun main(vararg args: String) {
    runApplication<CoroutinesKotlinApplication>(*args)
}
