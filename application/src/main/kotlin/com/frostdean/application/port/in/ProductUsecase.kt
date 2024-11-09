package com.frostdean.application.port.`in`

import com.frostdean.application.ProductUsecaseImpl
import com.frostdean.application.ProductUsecaseImpl.ProductDetail
import com.frostdean.domain.Product
import java.math.BigDecimal
import java.time.LocalDateTime

interface ProductUsecase {

    fun createProduct(command: ProductCreateCommand): ProductResult
    fun updateProduct(command: ProductUpdateCommand): ProductResult
    fun deleteProduct(productId: Long)
    fun getCheapestCoordiByCategory(): List<ProductDetail>

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

    fun getCheapestCoordiByBrand(): ProductUsecaseImpl.SingleBrandCheapestCoordi
    fun getCheapestAndMostExpensiveProductByCategory(categoryId: Long): ProductUsecaseImpl.CheapestAndMostExpensive
}


