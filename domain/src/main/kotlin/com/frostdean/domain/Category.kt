package com.frostdean.domain

import java.time.LocalDateTime

data class Category(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)