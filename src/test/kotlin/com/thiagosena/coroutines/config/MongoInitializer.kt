package com.thiagosena.coroutines.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MongoDBContainer
import kotlin.annotation.AnnotationTarget.CLASS

class MongoInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(context: ConfigurableApplicationContext) {
        val addedProperties = listOf(
            "spring.data.mongodb.uri=${MongoContainerSingleton.instance.replicaSetUrl}"
        )
        TestPropertyValues.of(addedProperties).applyTo(context.environment)
    }

}

object MongoContainerSingleton {
    val instance: MongoDBContainer by lazy { startMongoContainer() }
    private fun startMongoContainer(): MongoDBContainer =
        MongoDBContainer("mongo:4.4.2")
            .withReuse(true)
            .apply { start() }
}

@Target(CLASS)
@SpringBootTest
@ContextConfiguration(initializers = [MongoInitializer::class])
annotation class MongoSpringBootTest
