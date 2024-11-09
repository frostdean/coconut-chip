package com.frostdean.infrastructure.jpa

import com.frostdean.domain.Category
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
@Table(name = "categories")
data class JpaCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
){
    fun toDomain() = Category(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: Category) = JpaCategory(
            id = domain.id,
            name = domain.name,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}

interface JpaCategoryRepository : JpaRepository<JpaCategory, Long> {
    fun findByName(name: String): JpaCategory?
}