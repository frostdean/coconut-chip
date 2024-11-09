package com.frostdean.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BrandTest {
    @Test
    fun test_create() {
        val name = "신규"

        val brand = Brand.create(name)

        assertEquals(name, brand.name)
    }

    @Test
    fun test_createEmptyName() {
        val name = ""

        assertThrows(IllegalArgumentException::class.java) {
            Brand.create(name)
        }
    }

    @Test
    fun test_update() {
        val name = "신규"
        val brand = Brand.create(name)

        val updatedName = "수정"
        val updatedBrand = brand.update(updatedName)

        assertEquals(updatedName, updatedBrand.name)
    }

    @Test
    fun test_updateEmptyName() {
        val name = "신규"
        val brand = Brand.create(name)

        val updatedName = ""

        assertThrows(IllegalArgumentException::class.java) {
            brand.update(updatedName)
        }
    }
}