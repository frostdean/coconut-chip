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
import java.time.LocalDateTime
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
    fun updateProduct_all() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = 3,
            brandId = 4,
            name = "수정상품",
            price = BigDecimal(10000)
        )
        val product = Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        every { mockGetProductPort.findById(cmd.productId) } returns product

        every { mockGetCategoryPort.existsById(cmd.categoryId!!) } returns true
        every { mockGetBrandPort.existsById(cmd.brandId!!) } returns true

        every { mockSaveProductPort.save(any()) } returns product.update(
            categoryId = cmd.categoryId,
            brandId = cmd.brandId,
            name = cmd.name,
            price = cmd.price
        )

        val result = sut.updateProduct(cmd)

        verify { mockSaveProductPort.save(any()) }

        assertEquals(cmd.categoryId, result.categoryId)
        assertEquals(cmd.brandId, result.brandId)
        assertEquals(cmd.name, result.name)
        assertEquals(cmd.price, result.price)
    }

    @Test
    fun test_updateProduct_partial() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = null,
            brandId = null,
            name = "수정상품",
            price = BigDecimal(10000)
        )
        val product = Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        every { mockGetProductPort.findById(cmd.productId) } returns product

        every { mockSaveProductPort.save(any()) } returns product.update(
            categoryId = cmd.categoryId,
            brandId = cmd.brandId,
            name = cmd.name,
            price = cmd.price
        )

        val result = sut.updateProduct(cmd)

        verify { mockSaveProductPort.save(any()) }

        assertEquals(product.categoryId, result.categoryId)
        assertEquals(product.brandId, result.brandId)
        assertEquals(cmd.name, result.name)
        assertEquals(cmd.price, result.price)
    }

    @Test
    fun test_updateProduct_productNotFound() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = 1,
            brandId = 1,
            name = "수정상품",
            price = BigDecimal(10000)
        )
        every { mockGetProductPort.findById(cmd.productId) } returns null

        assertThrows(IllegalArgumentException::class.java) {
            sut.updateProduct(cmd)
        }
    }

    @Test
    fun test_updateProduct_categoryNotFound() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = 1,
            brandId = 1,
            name = "수정상품",
            price = BigDecimal(10000)
        )
        every { mockGetProductPort.findById(cmd.productId) } returns Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        every { mockGetCategoryPort.existsById(cmd.categoryId!!) } returns false

        assertThrows(IllegalArgumentException::class.java) {
            sut.updateProduct(cmd)
        }
    }


    @Test
    fun test_updateProduct_brandNotFound() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = 1,
            brandId = 1,
            name = "수정상품",
            price = BigDecimal(10000)
        )
        every { mockGetProductPort.findById(cmd.productId) } returns Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        every { mockGetCategoryPort.existsById(cmd.categoryId!!) } returns true
        every { mockGetBrandPort.existsById(cmd.brandId!!) } returns false

        assertThrows(IllegalArgumentException::class.java) {
            sut.updateProduct(cmd)
        }
    }

    @Test
    fun test_updateProduct_nothingToUpdate() {
        val cmd = ProductUsecase.ProductUpdateCommand(
            productId = 1,
            categoryId = null,
            brandId = null,
            name = null,
            price = null
        )
        every { mockGetProductPort.findById(cmd.productId) } returns Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        assertThrows(IllegalArgumentException::class.java) {
            sut.updateProduct(cmd)
        }
    }

    @Test
    fun test_deleteProduct() {
        val product = Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val product2 = Product(
            id = 2,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(50000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        every { mockGetProductPort.findById(product.id) } returns product
        every { mockGetProductPort.findByCategoryIdAndBrandId(product.categoryId, product.brandId) } returns listOf(
            product,
            product2
        )
        every { mockSaveProductPort.deleteById(product.id) } returns Unit

        sut.deleteProduct(product.id)

        verify { mockSaveProductPort.deleteById(product.id) }
    }

    @Test
    fun test_deleteProduct_productNotFound() {
        val productId = 1L
        every { mockGetProductPort.findById(productId) } returns null

        assertThrows(IllegalArgumentException::class.java) {
            sut.deleteProduct(productId)
        }
    }

    @Test
    fun test_deleteProduct_brandCategoryUnderTwo() {
        val product = Product(
            id = 1,
            categoryId = 1,
            brandId = 1,
            name = "기존상품",
            price = BigDecimal(20000),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        every { mockGetProductPort.findById(product.id) } returns product
        every { mockGetProductPort.findByCategoryIdAndBrandId(product.categoryId, product.brandId) } returns listOf(
            product
        )

        assertThrows(IllegalArgumentException::class.java) {
            sut.deleteProduct(product.id)
        }
    }

    @Test
    fun test_getCheapestCoordi() {
        every { mockGetCategoryPort.findAll() } returns testCategories
        every { mockGetBrandPort.findAll() } returns testBrands
        every { mockGetProductPort.findAll() } returns testProducts

        val result = sut.getCheapestCoordi()

        assertEquals(BigDecimal(96800), result.sumOf { it.price })
    }

    @Test
    fun test_getCheapestCoordi_categoryNotFound() {
        every { mockGetCategoryPort.findAll() } returns testCategories.filter { it.id != 1L }
        every { mockGetBrandPort.findAll() } returns testBrands
        every { mockGetProductPort.findAll() } returns testProducts

        assertThrows(IllegalArgumentException::class.java) {
            sut.getCheapestCoordi()
        }
    }

    @Test
    fun test_getCheapestCoordi_brandNotFound() {
        every { mockGetCategoryPort.findAll() } returns testCategories
        every { mockGetBrandPort.findAll() } returns testBrands.filter { it.id != 1L }
        every { mockGetProductPort.findAll() } returns testProducts

        assertThrows(IllegalArgumentException::class.java) {
            sut.getCheapestCoordi()
        }
    }

    @Test
    fun test_getCheapestCoordiByBrand() {
        every { mockGetBrandPort.findAll() } returns testBrands
        every { mockGetCategoryPort.findAll() } returns testCategories

        every { mockGetProductPort.findByBrandId(1) } returns testProducts.filter { it.brandId == 1L }
        every { mockGetProductPort.findByBrandId(2) } returns testProducts.filter { it.brandId == 2L }
        every { mockGetProductPort.findByBrandId(3) } returns testProducts.filter { it.brandId == 3L }

        val result = sut.getCheapestCoordiByBrand()


        assertEquals(2, result.brandId)
        assertEquals(BigDecimal(109800), result.productList.sumOf { it.price })
    }

    @Test
    fun test_getCheapestCoordiByBrand_categoryNotFound() {
        every { mockGetBrandPort.findAll() } returns testBrands
        every { mockGetCategoryPort.findAll() } returns testCategories.filter { it.id != 1L }

        every { mockGetProductPort.findByBrandId(1) } returns testProducts.filter { it.brandId == 1L }
        every { mockGetProductPort.findByBrandId(2) } returns testProducts.filter { it.brandId == 2L }
        every { mockGetProductPort.findByBrandId(3) } returns testProducts.filter { it.brandId == 3L }

        assertThrows(IllegalArgumentException::class.java) {
            sut.getCheapestCoordiByBrand()
        }
    }


    @Test
    fun test_getCheapestAndMostExpensiveProductByCategory() {
        val categoryId = 1L
        every { mockGetCategoryPort.findById(categoryId) } returns testCategories.first { it.id == categoryId }
        every { mockGetProductPort.findByCategoryId(categoryId) } returns testProducts.filter { it.categoryId == categoryId }
        every { mockGetBrandPort.findById(any()) } returns testBrands.first { it.id == 1L }

        val result = sut.getCheapestAndMostExpensiveProductByCategory(categoryId)

        result.let {
            assertEquals(BigDecimal(10000), it.cheapest.price)
            assertEquals(BigDecimal(19000), it.mostExpensive.price)
        }
    }

    @Test
    fun test_getCheapestAndMostExpensiveProductByCategory_CategoryNotFound() {
        val categoryId = 1L
        every { mockGetCategoryPort.findById(categoryId) } returns null

        assertThrows(IllegalArgumentException::class.java) {
            sut.getCheapestAndMostExpensiveProductByCategory(categoryId)
        }
    }

    @Test
    fun test_getCheapestAndMostExpensiveProductByCategory_BrandNotFound() {
        val categoryId = 1L
        every { mockGetCategoryPort.findById(categoryId) } returns testCategories.first { it.id == categoryId }
        every { mockGetProductPort.findByCategoryId(categoryId) } returns testProducts.filter { it.categoryId == categoryId }
        every { mockGetBrandPort.findById(any()) } returns null

        assertThrows(IllegalArgumentException::class.java) {
            val result = sut.getCheapestAndMostExpensiveProductByCategory(categoryId)
        }
    }
}