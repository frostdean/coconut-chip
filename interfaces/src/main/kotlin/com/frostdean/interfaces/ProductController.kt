package com.frostdean.interfaces

import com.frostdean.application.port.`in`.ProductUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productUsecase: ProductUsecase
) {

    @PostMapping
    fun createProduct() {

    }

    @PatchMapping("/{productId}")
    fun updateProduct(@PathVariable productId: String) {

    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: String) {

    }


    @GetMapping("/c-a-e")
    fun getCheapestAndMostExpensive(): ResponseEntity<LowestAndHighestResponse> {
        return LowestAndHighestResponse(
            categoryName = "category1",
            lowest = LowestAndHighestResponse.ProductResponse(
                productId = 1,
                productName = "product1",
                price = BigDecimal(1000)
            ),
            highest = LowestAndHighestResponse.ProductResponse(
                productId = 2,
                productName = "product2",
                price = BigDecimal(2000)
            )
        ).let {
            ResponseEntity.ok(it)
        }

    }

    data class LowestAndHighestResponse(
        val categoryName: String,
        val lowest: ProductResponse,
        val highest: ProductResponse
    ) {
        data class ProductResponse(
            val productId: Long,
            val productName: String,
            val price: BigDecimal
        )

    }


}


