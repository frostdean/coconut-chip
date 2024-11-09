package com.frostdean.interfaces

import com.frostdean.application.port.`in`.ProductUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/products/coordi")
class ProductCoordiController(
    private val productUsecase: ProductUsecase
) {

    @GetMapping("/cheapest")
    fun getLowestProducts(): ResponseEntity<CheapestCoordiResponse> {
        return CheapestCoordiResponse(
            lowestProduct = listOf(
                CheapestCoordiResponse.LowestProductResponse(
                    categoryId = 1,
                    productId = 1,
                    brandId = 1,
                    price = BigDecimal(1000),
                    categoryName = "category1",
                    productName = "product1",
                    brandName = "brand1"
                )
            ),
            totalSumPrice = BigDecimal(1000)
        ).let {
            ResponseEntity.ok(it)
        }

    }

    @GetMapping("/cheapest-brand")
    fun getPriceByBrand(): CheapestBrandCoordiResponse {
        return CheapestBrandCoordiResponse(
            cheapest = CheapestBrandCoordiResponse.CheapestBrandResponse(
                brandId = 1,
                brandName = "brand1",
                itemList = listOf(
                    CheapestBrandCoordiResponse.CheapestBrandResponse.ItemResponse(
                        productId = 1,
                        productName = "product1",
                        price = BigDecimal(1000)
                    )
                ),
                totalSumPrice = BigDecimal(1000)
            )
        )

    }

    data class CheapestBrandCoordiResponse(
        val cheapest: CheapestBrandResponse
    ) {
        data class CheapestBrandResponse(
            val brandId: Long,
            val brandName: String,
            val itemList: List<ItemResponse>,
            val totalSumPrice: BigDecimal
        ) {
            data class ItemResponse(
                val productId: Long,
                val productName: String,
                val price: BigDecimal
            )
        }
    }


    data class CheapestCoordiResponse(
        val lowestProduct: List<LowestProductResponse>,
        val totalSumPrice: BigDecimal
    ) {
        data class LowestProductResponse(
            val categoryId: Long,
            val productId: Long,
            val brandId: Long,
            val price: BigDecimal,
            val categoryName: String,
            val productName: String,
            val brandName: String
        )
    }
}

