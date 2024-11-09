package com.frostdean.application.port.`in`

import com.frostdean.domain.Product
import java.math.BigDecimal
import java.time.LocalDateTime

interface ProductUsecase {

    fun createProduct(command: ProductCreateCommand): ProductResult
    fun updateProduct(command: ProductUpdateCommand): ProductResult
    fun deleteProduct(productId: Long)
    fun getCheapestCoordi(): List<ProductDetail>

    data class ProductCreateCommand(
        val categoryId: Long,
        val brandId: Long,
        val name: String,
        val price: BigDecimal
    )

    data class ProductUpdateCommand(
        val productId: Long,
        val categoryId: Long?,
        val brandId: Long?,
        val name: String?,
        val price: BigDecimal?
    )

    data class ProductResult(
        val id: Long,
        val categoryId: Long,
        val brandId: Long,
        val name: String,
        val price: BigDecimal,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    ) {
        companion object {
            fun from(product: Product) = ProductResult(
                id = product.id,
                categoryId = product.categoryId,
                brandId = product.brandId,
                name = product.name,
                price = product.price,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
            )
        }
    }

    data class SingleBrandCheapestCoordi(
        val brandId: Long,
        val brandName: String,
        val productList: List<ProductDetail>
    )

    data class ProductDetail(
        val id: Long,
        val categoryId: Long,
        val categoryName: String,
        val brandId: Long,
        val brandName: String,
        val name: String,
        val price: BigDecimal,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )

    fun getCheapestCoordiByBrand(): SingleBrandCheapestCoordi


    data class CheapestAndMostExpensive(
        val cheapest: ProductDetail,
        val mostExpensive: ProductDetail
    )

    fun getCheapestAndMostExpensiveProductByCategory(categoryId: Long): CheapestAndMostExpensive
}


