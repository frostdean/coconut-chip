package com.frostdean.interfaces.controllers

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.interfaces.common.CoconutResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/products/coordi")
class ProductCoordiController(
    private val productUsecase: ProductUsecase
) {

    // 구현 1 API
    @GetMapping("/cheapest")
    fun getCheapestCoordi(): CoconutResponse<CheapestCoordiResponse> {
        val result = productUsecase.getCheapestCoordi()

        return CheapestCoordiResponse(
            lowestProduct = result.map {
                CheapestCoordiResponse.LowestProductResponse(
                    categoryId = it.categoryId,
                    productId = it.id,
                    brandId = it.brandId,
                    price = it.price,
                    categoryName = it.categoryName,
                    productName = it.name,
                    brandName = it.brandName
                )
            },
            totalSumPrice = result.sumOf { it.price }
        ).let {
            CoconutResponse.success(it)
        }

    }

    // 구현 2 API
    @GetMapping("/cheapest-brand")
    fun getCheapestByBrand(): CoconutResponse<CheapestSingleBrandCoordiResponse> {
        val result = productUsecase.getCheapestCoordiByBrand()
        return CheapestSingleBrandCoordiResponse(
            cheapest = CheapestSingleBrandCoordiResponse.CheapestBrandResponse(
                brandId = result.brandId,
                brandName = result.brandName,
                itemList = result.productList.map {
                    CheapestSingleBrandCoordiResponse.CheapestBrandResponse.ItemResponse(
                        productId = it.id,
                        productName = it.name,
                        categoryName = it.categoryName,
                        price = it.price
                    )
                },
                totalSumPrice = result.productList.sumOf { it.price }
            )
        ).let {
            CoconutResponse.success(it)
        }
    }

    data class CheapestSingleBrandCoordiResponse(
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
                val categoryName: String,
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

