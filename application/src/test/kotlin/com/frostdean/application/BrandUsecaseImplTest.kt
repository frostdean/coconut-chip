package com.frostdean.application

import com.frostdean.application.port.`in`.BrandUsecase
import com.frostdean.application.port.out.GetBrandPort
import com.frostdean.application.port.out.SaveBrandPort
import com.frostdean.application.port.out.SaveProductPort
import com.frostdean.domain.Brand
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class BrandUsecaseImplTest {

    private val mockGetBrandPort: GetBrandPort = mockk()
    private val mockSaveBrandPort: SaveBrandPort = mockk()
    private val mockSaveProductPort: SaveProductPort = mockk()
    private val sut = BrandUsecaseImpl(
        getBrandPort = mockGetBrandPort,
        saveBrandPort = mockSaveBrandPort,
        saveProductPort = mockSaveProductPort
    )

    @Test
    fun test_createBrand() {
        val name = "신규"
        val brand = BrandUsecase.BrandCreateCommand(name = name)

        every { mockGetBrandPort.existsByName(name) } returns false
        every { mockSaveBrandPort.save(any()) } returns Brand.create(brand.name)

        val result = sut.createBrand(brand)

        assertEquals(name, result.name)
    }

    @Test
    fun test_createDuplicated() {
        val name = "신규"
        val brand = BrandUsecase.BrandCreateCommand(name = name)

        every { mockGetBrandPort.existsByName(name) } returns true

        assertThrows(IllegalArgumentException::class.java) {
            sut.createBrand(brand)
        }
    }

    @Test
    fun test_updateBrand() {
        val name = "신규"
        val brand = BrandUsecase.BrandUpdateCommand(brandId = 1, name = name)

        every { mockGetBrandPort.findById(brand.brandId) } returns Brand.create("기존")
        every { mockGetBrandPort.existsByName(name) } returns false
        every { mockSaveBrandPort.save(any()) } returns Brand.create(brand.name)

        val result = sut.updateBrand(brand)

        assertEquals(name, result.name)

    }

    @Test
    fun test_updateDuplicated() {
        val name = "신규"
        val brand = BrandUsecase.BrandUpdateCommand(brandId = 1, name = name)

        every { mockGetBrandPort.findById(brand.brandId) } returns Brand.create("기존")
        every { mockGetBrandPort.existsByName(name) } returns true

        assertThrows(IllegalArgumentException::class.java) {
            sut.updateBrand(brand)
        }
    }

    @Test
    fun deleteBrand() {
        val brandId = 0L

        every { mockGetBrandPort.findById(brandId) } returns Brand.create("기존")
        every { mockSaveBrandPort.deleteById(brandId) } returns Unit
        every { mockSaveProductPort.deleteByBrandId(brandId) } returns Unit

        sut.deleteBrand(brandId)

        verify(exactly = 1) { mockSaveBrandPort.deleteById(brandId) }
        verify(exactly = 1) { mockSaveProductPort.deleteByBrandId(brandId) }
    }

    @Test
    fun deleteBrandNotFound() {
        val brandId = 0L

        every { mockGetBrandPort.findById(brandId) } returns null

        assertThrows(IllegalArgumentException::class.java) {
            sut.deleteBrand(brandId)
        }

        verify(exactly = 0) { mockSaveBrandPort.deleteById(brandId) }
        verify(exactly = 0) { mockSaveProductPort.deleteByBrandId(brandId) }

    }
}