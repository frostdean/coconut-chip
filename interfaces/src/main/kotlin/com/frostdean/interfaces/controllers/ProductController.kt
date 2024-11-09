package com.frostdean.interfaces.controllers

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.interfaces.common.CoconutResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productUsecase: ProductUsecase
) {
    // 구현 4 API
    @PostMapping
    fun createProduct(@RequestBody request: ProductCreateRequest): CoconutResponse<ProductResponse> {
        val command = ProductUsecase.ProductCreateCommand(
            categoryId = request.categoryId,
            brandId = request.brandId,
            name = request.name,
            price = request.price
        )
        productUsecase.createProduct(command).let {
            return CoconutResponse.success(
                ProductResponse(
                    productId = it.id,
                    categoryId = it.categoryId,
                    brandId = it.brandId,
                    productName = it.name,
                    price = it.price
                )
            )
        }
    }

    // 구현 4 API
    @PatchMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductUpdateRequest
    ): CoconutResponse<ProductResponse> {
        val command = ProductUsecase.ProductUpdateCommand(
            productId = productId,
            categoryId = request.categoryId,
            brandId = request.brandId,
            name = request.name,
            price = request.price
        )
        productUsecase.updateProduct(command).let {
            return CoconutResponse.success(
                ProductResponse(
                    productId = it.id,
                    categoryId = it.categoryId,
                    brandId = it.brandId,
                    productName = it.name,
                    price = it.price
                )
            )
        }
    }

    // 구현 4 API
    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long): CoconutResponse<Unit> {
        productUsecase.deleteProduct(productId)
        return CoconutResponse.emptyOk()
    }


    // 구현 3 API
    @GetMapping("/cheapest-and-most-expensive")
    fun getCheapestAndMostExpensive(@RequestParam categoryId: Long): CoconutResponse<LowestAndHighestResponse> {
        productUsecase.getCheapestAndMostExpensiveProductByCategory(categoryId).let {
            return LowestAndHighestResponse(
                categoryName = it.cheapest.categoryName,
                lowest = LowestAndHighestResponse.ProductResponse(
                    productId = it.cheapest.id,
                    productName = it.cheapest.name,
                    brandName = it.cheapest.brandName,
                    price = it.cheapest.price
                ),
                highest = LowestAndHighestResponse.ProductResponse(
                    productId = it.mostExpensive.id,
                    productName = it.mostExpensive.name,
                    brandName = it.mostExpensive.brandName,
                    price = it.mostExpensive.price
                )
            ).let {
                CoconutResponse.success(it)
            }
        }
    }

    data class ProductCreateRequest(
        val categoryId: Long,
        val brandId: Long,
        val name: String,
        val price: BigDecimal
    )

    data class ProductUpdateRequest(
        val categoryId: Long?,
        val brandId: Long?,
        val name: String?,
        val price: BigDecimal?
    )

    data class ProductResponse(
        val productId: Long,
        val brandId: Long,
        val categoryId: Long,
        val productName: String,
        val price: BigDecimal
    )

    data class LowestAndHighestResponse(
        val categoryName: String,
        val lowest: ProductResponse,
        val highest: ProductResponse
    ) {
        data class ProductResponse(
            val productId: Long,
            val productName: String,
            val brandName: String,
            val price: BigDecimal
        )

    }


}


