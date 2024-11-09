package com.frostdean.domain

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import kotlin.test.Test

class CategoryTest {
    @Test
    fun test_create() {
        val name = "신규"

        val category = Category(
            id = 0,
            name = name,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        assertEquals(name, category.name)
    }

}