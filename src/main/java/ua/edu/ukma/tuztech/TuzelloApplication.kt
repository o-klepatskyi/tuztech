package ua.edu.ukma.tuztech

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = ["ua.edu.ukma.tuztech"])
class TuzelloApplication

fun main(args: Array<String>) {
    runApplication<TuzelloApplication>(*args)
}