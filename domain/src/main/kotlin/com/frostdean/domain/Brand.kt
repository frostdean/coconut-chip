package com.frostdean.domain

import java.time.LocalDateTime

data class Brand(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    init {
        require(name.trim().isNotBlank()) { "브랜드명은 공백일 수 없습니다" }
    }

    fun update(name: String): Brand {
        return Brand(
            id = this.id,
            name = name,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now()
        )
    }

    companion object {
        fun create(
            name: String
        ) = Brand(
            id = 0,
            name = name,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}