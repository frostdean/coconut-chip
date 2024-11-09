package com.frostdean.infrastructure.jpa

import com.frostdean.domain.Product
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class JpaProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val categoryId: Long,
    val brandId: Long,
    val name: String,
    val price: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {

    fun toDomain() = Product(
        id = id,
        categoryId = categoryId,
        brandId = brandId,
        name = name,
        price = price,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: Product) = JpaProduct(
            id = domain.id,
            categoryId = domain.categoryId,
            brandId = domain.brandId,
            name = domain.name,
            price = domain.price,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}

interface JpaProductRepository : JpaRepository<JpaProduct, Long> {
    fun findByBrandId(brandId: Long): List<JpaProduct>
    fun findByCategoryId(categoryId: Long): List<JpaProduct>
}