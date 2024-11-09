package com.frostdean.interfaces.controllers

import com.frostdean.application.ProductUsecaseImpl
import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.interfaces.GlobalExceptionHandler
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class ProductCoordiControllerTest {

    private val mockProductUsecase: ProductUsecase = mockk()
    private val sut = MockMvcBuilders
        .standaloneSetup(
            ProductCoordiController(
                productUsecase = mockProductUsecase
            )
        )
        .setControllerAdvice(GlobalExceptionHandler())
        .build()

    @Test
    fun test_getCheapestCoordi() {
        every { mockProductUsecase.getCheapestCoordi() } returns listOf(
            ProductUsecase.ProductDetail(
                id = 1,
                categoryId = 1,
                brandId = 1,
                name = "A_상의",
                price = 100.toBigDecimal(),
                categoryName = "상의",
                brandName = "A",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            ),
            ProductUsecase.ProductDetail(
                id = 2,
                categoryId = 2,
                brandId = 2,
                name = "B_하의",
                price = 200.toBigDecimal(),
                categoryName = "하의",
                brandName = "B",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/coordi/cheapest")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                        {
                        "code": "OK",
                        "data": {
                          "lowestProduct": [
                            {
                              "categoryId": 1,
                              "productId": 1,
                              "brandId": 1,
                              "price": 100,
                              "categoryName": "상의",
                              "productName": "A_상의",
                              "brandName": "A"
                            },
                            {
                              "categoryId": 2,
                              "productId": 2,
                              "brandId": 2,
                              "price": 200,
                              "categoryName": "하의",
                              "productName": "B_하의",
                              "brandName": "B"
                            }
                           ],
                          "totalSumPrice": 300
                        }
                        }
                    """.trimIndent()
                )

            )
    }

    @Test
    fun test_getCheapestCoordi_IllegalArgument() {
        every { mockProductUsecase.getCheapestCoordi() } throws IllegalArgumentException("Category 1 was not found")

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/coordi/cheapest")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                        {
                        "code": "BAD_REQUEST",
                        "message": "Category 1 was not found",
                        "displayMessage": "Category 1 was not found"
                        }
                    """.trimIndent()
                )

            )
    }


    @Test
    fun test_getCheapestByBrand() {
        every { mockProductUsecase.getCheapestCoordiByBrand() } returns ProductUsecase.SingleBrandCheapestCoordi(
            brandId = 1,
            brandName = "A",
            productList = listOf(
                ProductUsecase.ProductDetail(
                    id = 1,
                    categoryId = 1,
                    brandId = 1,
                    name = "A_상의",
                    price = 100.toBigDecimal(),
                    categoryName = "상의",
                    brandName = "A",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                ),
                ProductUsecase.ProductDetail(
                    id = 2,
                    categoryId = 2,
                    brandId = 1,
                    name = "A_하의",
                    price = 200.toBigDecimal(),
                    categoryName = "하의",
                    brandName = "A",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                )
            )
        )

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/coordi/cheapest-brand")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                        {
                        "code": "OK",
                        "data": {
                          "cheapest": {
                            "brandId": 1,
                            "brandName": "A",
                            "itemList": [
                              {
                                "productId": 1,
                                "productName": "A_상의",
                                "categoryName": "상의",
                                "price": 100
                              },
                              {
                                "productId": 2,
                                "productName": "A_하의",
                                "categoryName": "하의",
                                "price": 200
                              }
                            ],
                            "totalSumPrice": 300
                          }
                        }
                        }
                    """.trimIndent()
                )

            )
    }

    @Test
    fun test_getCheapestByBrand_IllegalArgument() {
        every { mockProductUsecase.getCheapestCoordiByBrand() } throws IllegalArgumentException("Category 1 was not found")

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/coordi/cheapest-brand")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                        {
                        "code": "BAD_REQUEST",
                        "message": "Category 1 was not found",
                        "displayMessage": "Category 1 was not found"
                        }
                    """.trimIndent()
                )

            )
    }
}