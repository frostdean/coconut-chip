package com.frostdean

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.frostdean.infrastructure",
        "com.frostdean.application",
        "com.frostdean.interfaces",
        "com.frostdean.domain"
    ]
)
class CoconutChipApplication

fun main(args: Array<String>) {
    runApplication<CoconutChipApplication>(*args)
}
