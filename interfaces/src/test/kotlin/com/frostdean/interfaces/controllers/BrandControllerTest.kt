package com.frostdean.interfaces.controllers

import com.frostdean.application.port.`in`.BrandUsecase
import com.frostdean.interfaces.GlobalExceptionHandler
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class BrandControllerTest {

    private val mockBrandUsecase: BrandUsecase = mockk()
    private val sut = MockMvcBuilders
        .standaloneSetup(
            BrandController(
                brandUsecase = mockBrandUsecase
            )
        )
        .setControllerAdvice(GlobalExceptionHandler())
        .build()


    @Test
    fun test_createBrand() {
        every { mockBrandUsecase.createBrand(any()) } returns BrandUsecase.BrandResult(
            id = 1,
            name = "A",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        sut.perform(
            MockMvcRequestBuilders.post("/api/v1/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "A"
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
                            "brandId": 1,
                            "brandName": "A"
                        }
                    }
                    """
                )
            )
    }

    @Test
    fun test_createBrand_IllegalArgument() {
        every { mockBrandUsecase.createBrand(any()) } throws IllegalArgumentException("Duplicated")

        sut.perform(
            MockMvcRequestBuilders.post("/api/v1/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "A"
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Duplicated"
                    }
                    """
                )
            )
    }

    @Test
    fun test_updateBrand() {
        every { mockBrandUsecase.updateBrand(any()) } returns BrandUsecase.BrandResult(
            id = 1,
            name = "A",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        sut.perform(
            MockMvcRequestBuilders.patch("/api/v1/brands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "A"
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
                            "brandId": 1,
                            "brandName": "A"
                        }
                    }
                    """
                )
            )
    }

    @Test
    fun test_updateBrand_IllegalArgument() {
        every { mockBrandUsecase.updateBrand(any()) } throws IllegalArgumentException("Not found")

        sut.perform(
            MockMvcRequestBuilders.patch("/api/v1/brands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "A"
                    }
                    """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                    {
                        "code": "BAD_REQUEST",
                        "message": "Not found"
                    }
                    """
                )
            )
    }

    @Test
    fun test_deleteBrand() {
        every { mockBrandUsecase.deleteBrand(1) } returns Unit

        sut.perform(
            MockMvcRequestBuilders.delete("/api/v1/brands/1")
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
}