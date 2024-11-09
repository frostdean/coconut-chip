package com.frostdean.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class ProductTest {

    @Test
    fun test_create() {
        val name = "신규"
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())

        val product = Product.create(category.id, brand.id, name, price)

        assertEquals(name, product.name)
        assertEquals(price, product.price)
        assertEquals(brand.id, product.brandId)
        assertEquals(category.id, product.categoryId)
    }

    @Test
    fun test_createEmptyName() {
        val name = ""
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())

        assertThrows(IllegalArgumentException::class.java) {
            Product.create(category.id, brand.id, name, price)
        }
    }

    @Test
    fun test_createNegativePrice() {
        val name = "신규"
        val price = BigDecimal(-1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())

        assertThrows(IllegalArgumentException::class.java) {
            Product.create(category.id, brand.id, name, price)
        }
    }

    @Test
    fun test_update() {
        val name = "신규"
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())
        val product = Product.create(category.id, brand.id, name, price)

        val updatedName = "수정"
        val updatedPrice = BigDecimal(2000)
        val updatedProduct = product.update(name = updatedName, price = updatedPrice)

        assertEquals(updatedName, updatedProduct.name)
        assertEquals(updatedPrice, updatedProduct.price)
    }

    @Test
    fun test_updateEmptyName() {
        val name = "신규"
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())
        val product = Product.create(category.id, brand.id, name, price)

        val updatedName = ""
        val updatedPrice = BigDecimal(2000)

        assertThrows(IllegalArgumentException::class.java) {
            product.update(name = updatedName, price = updatedPrice)
        }
    }

    @Test
    fun test_updateNegativePrice() {
        val name = "신규"
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())
        val product = Product.create(category.id, brand.id, name, price)

        val updatedName = "수정"
        val updatedPrice = BigDecimal(-2000)

        assertThrows(IllegalArgumentException::class.java) {
            product.update(name = updatedName, price = updatedPrice)
        }
    }

    @Test
    fun test_updateNoChanges() {
        val name = "신규"
        val price = BigDecimal(1000)
        val brand = Brand.create("브랜드")
        val category = Category(1, "카테고리", LocalDateTime.now(), LocalDateTime.now())
        val product = Product.create(category.id, brand.id, name, price)

        assertThrows(IllegalArgumentException::class.java) {
            product.update(categoryId = null, brandId = null, name = null, price = null)
        }
    }

}