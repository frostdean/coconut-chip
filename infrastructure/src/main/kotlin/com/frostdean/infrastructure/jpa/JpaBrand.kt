package com.frostdean.infrastructure.jpa

import com.frostdean.domain.Brand
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
@Table(name = "brands")
data class JpaBrand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
){
    fun toDomain() = Brand(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: Brand) = JpaBrand(
            id = domain.id,
            name = domain.name,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}

interface JpaBrandRepository : JpaRepository<JpaBrand, Long> {
    fun findByName(name: String): JpaBrand?
    fun existsByName(name: String): Boolean
}