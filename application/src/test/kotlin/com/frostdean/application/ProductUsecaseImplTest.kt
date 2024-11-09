package com.frostdean.application

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.application.port.out.GetBrandPort
import com.frostdean.application.port.out.GetCategoryPort
import com.frostdean.application.port.out.GetProductPort
import com.frostdean.application.port.out.SaveProductPort
import com.frostdean.domain.Product
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class ProductUsecaseImplTest {
    private val mockGetProductPort: GetProductPort = mockk()
    private val mockGetBrandPort: GetBrandPort = mockk()
    private val mockGetCategoryPort: GetCategoryPort = mockk()
    private val mockSaveProductPort: SaveProductPort = mockk()

    private val sut = ProductUsecaseImpl(
        getProductPort = mockGetProductPort,
        getBrandPort = mockGetBrandPort,
        getCategoryPort = mockGetCategoryPort,
        saveProductPort = mockSaveProductPort
    )

    @Test
    fun test_createProduct() {

        val cmd = ProductUsecase.ProductCreateCommand(
            categoryId = 0,
            brandId = 0,
            name = "신규상품",
            price = BigDecimal(10000)
        )
        every { mockGetCategoryPort.existsById(cmd.categoryId) } returns true
        every { mockGetBrandPort.existsById(cmd.brandId) } returns true
        every { mockSaveProductPort.save(any()) } returns Product.create(
            categoryId = cmd.categoryId,
            brandId = cmd.brandId,
            name = cmd.name,
            price = cmd.price
        )

        val result = sut.createProduct(cmd)

        verify { mockSaveProductPort.save(any()) }

        assertEquals(cmd.categoryId, result.categoryId)
        assertEquals(cmd.brandId, result.brandId)
        assertEquals(cmd.name, result.name)
        assertEquals(cmd.price, result.price)

    }

    @Test
    fun test_createProduct_categoryNotFound() {
        val cmd = ProductUsecase.ProductCreateCommand(
            categoryId = 0,
            brandId = 0,
            name = "신규상품",
            price = BigDecimal(10000)
        )
        every { mockGetCategoryPort.existsById(cmd.categoryId) } returns false

        assertThrows(IllegalArgumentException::class.java) {
            sut.createProduct(cmd)
        }
    }

    @Test
    fun test_createProduct_brandNotFound() {
        val cmd = ProductUsecase.ProductCreateCommand(
            categoryId = 0,
            brandId = 0,
            name = "신규상품",
            price = BigDecimal(10000)
        )
        every { mockGetCategoryPort.existsById(cmd.categoryId) } returns true
        every { mockGetBrandPort.existsById(cmd.brandId) } returns false

        assertThrows(IllegalArgumentException::class.java) {
            sut.createProduct(cmd)
        }
    }

    @Test
    fun updateProduct() {
    }

    @Test
    fun deleteProduct() {
    }

    @Test
    fun getCheapestCoordiByCategory() {
    }

    @Test
    fun getCheapestCoordiByBrand() {
    }

    @Test
    fun getCheapestAndMostExpensiveProductByCategory() {
    }
}