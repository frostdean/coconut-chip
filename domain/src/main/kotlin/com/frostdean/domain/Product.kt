package com.frostdean.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
    val id: Long,
    val categoryId: Long,
    val brandId: Long,
    val name: String,
    val price: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    init {
        require(price >= BigDecimal.ZERO) { "가격은 0 보다 작을 수 없습니다" }
        require(name.trim().isNotBlank()) { "상품명은 공백일 수 없습니다" }
    }

    fun update(
        categoryId: Long? = null,
        brandId: Long? = null,
        name: String? = null,
        price: BigDecimal? = null
    ): Product {
        requireNotNull(categoryId ?: brandId ?: name ?: price) { "수정할 값이 없습니다" }

        return Product(
            id = this.id,
            categoryId = categoryId ?: this.categoryId,
            brandId = brandId ?: this.brandId,
            name = name ?: this.name,
            price = price ?: this.price,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now()
        )
    }

    companion object {
        fun create(
            categoryId: Long,
            brandId: Long,
            name: String,
            price: BigDecimal
        ) = Product(
            id = 0,
            categoryId = categoryId,
            brandId = brandId,
            name = name,
            price = price,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}