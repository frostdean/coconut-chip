package com.frostdean.interfaces.controllers

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.interfaces.GlobalExceptionHandler
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class ProductControllerTest {

    private val mockProductUsecase: ProductUsecase = mockk()
    private val sut = MockMvcBuilders
        .standaloneSetup(
            ProductController(
                productUsecase = mockProductUsecase
            )
        )
        .setControllerAdvice(GlobalExceptionHandler())
        .build()

    @Test
    fun test_createProduct() {
        every { mockProductUsecase.createProduct(any()) } returns ProductUsecase.ProductResult(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "A",
            price = 100.toBigDecimal(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        sut.perform(
            MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "categoryId": 1,
                        "brandId": 1,
                        "name": "A",
                        "price": 100
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "OK",
                        "data": {
                            "productId": 1,
                            "categoryId": 1,
                            "brandId": 1,
                            "productName": "A",
                            "price": 100
                        }
                    }
                    """
                )
            )
    }

    @Test
    fun test_createProduct_IllegalArgument() {
        every { mockProductUsecase.createProduct(any()) } throws IllegalArgumentException("Category 1 not found")

        sut.perform(
            MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "categoryId": 1,
                        "brandId": 1,
                        "name": "A",
                        "price": 100
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Category 1 not found",
                        "displayMessage": "Category 1 not found"
                    }
                    """
                )
            )
    }

    @Test
    fun test_updateProduct() {
        every { mockProductUsecase.updateProduct(any()) } returns ProductUsecase.ProductResult(
            id = 1,
            categoryId = 2,
            brandId = 2,
            name = "A",
            price = 100.toBigDecimal(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        sut.perform(
            MockMvcRequestBuilders.patch("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "categoryId": null,
                        "brandId": null,
                        "name": "A",
                        "price": 100
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "OK",
                        "data": {
                            "productId": 1,
                            "categoryId": 2,
                            "brandId": 2,
                            "productName": "A",
                            "price": 100
                        }
                    }
                    """
                )
            )
    }

    @Test
    fun test_updateProduct_IllegalArgument() {
        every { mockProductUsecase.updateProduct(any()) } throws IllegalArgumentException("Category 1 not found")

        sut.perform(
            MockMvcRequestBuilders.patch("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "categoryId": 1,
                        "brandId": 1,
                        "name": "A",
                        "price": 100
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Category 1 not found",
                        "displayMessage": "Category 1 not found"
                    }
                    """
                )
            )
    }

    @Test
    fun test_deleteProduct() {
        every { mockProductUsecase.deleteProduct(1) } returns Unit

        sut.perform(
            MockMvcRequestBuilders.delete("/api/v1/products/1")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "OK"
                    }
                    """
                )
            )
    }

    @Test
    fun test_deleteProduct_IllegalArgument() {
        every { mockProductUsecase.deleteProduct(1) } throws IllegalArgumentException("Product 1 not found")

        sut.perform(
            MockMvcRequestBuilders.delete("/api/v1/products/1")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Product 1 not found",
                        "displayMessage": "Product 1 not found"
                    }
                    """
                )
            )
    }

    @Test
    fun test_getCheapestAndMostExpensive() {
        every { mockProductUsecase.getCheapestAndMostExpensiveProductByCategory(any()) } returns ProductUsecase.CheapestAndMostExpensive(
            cheapest = ProductUsecase.ProductDetail(
                id = 1,
                categoryId = 1,
                categoryName = "A",
                brandId = 1,
                brandName = "A",
                name = "A",
                price = 100.toBigDecimal(),
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            ),
            mostExpensive = ProductUsecase.ProductDetail(
                id = 2,
                categoryId = 1,
                categoryName = "A",
                brandId = 2,
                brandName = "B",
                name = "B",
                price = 200.toBigDecimal(),
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/cheapest-and-most-expensive").queryParam("categoryId", "1")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "OK",
                        "data": {
                            "categoryName": "A",
                            "cheapest": {
                                "productId": 1,
                                "brandName": "A",
                                "productName": "A",
                                "price": 100
                            },
                            "mostExpensive": {
                                "productId": 2,
                                "brandName": "B",
                                "productName": "B",
                                "price": 200
                            }
                        }
                    }
                    """
                )
            )
    }

    @Test
    fun test_getCheapestAndMostExpensive_IllegalArgument() {
        every { mockProductUsecase.getCheapestAndMostExpensiveProductByCategory(any()) } throws IllegalArgumentException("Category 1 not found")

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/cheapest-and-most-expensive").queryParam("categoryId", "1")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Category 1 not found",
                        "displayMessage": "Category 1 not found"
                    }
                    """
                )
            )
    }

    @Test
    fun test_getCheapestAndMostExpensive_InternalServerError() {
        every { mockProductUsecase.getCheapestAndMostExpensiveProductByCategory(any()) } throws RuntimeException("Internal Server Error")

        sut.perform(
            MockMvcRequestBuilders.get("/api/v1/products/cheapest-and-most-expensive").queryParam("categoryId", "1")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "INTERNAL_SERVER_ERROR",
                        "displayMessage": "시스템 오류가 발생했습니다"
                    }
                    """
                )
            )
    }
}