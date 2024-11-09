package com.frostdean.application.port.`in`

import com.frostdean.domain.Brand
import java.time.LocalDateTime

interface BrandUsecase {
    fun createBrand(command: BrandCreateCommand): BrandResult
    fun updateBrand(command: BrandUpdateCommand): BrandResult
    fun deleteBrand(brandId: Long)

    data class BrandCreateCommand(
        val name: String
    )

    data class BrandUpdateCommand(
        val brandId: Long,
        val name: String
    )

    data class BrandResult(
        val id: Long,
        val name: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    ) {
        companion object {
            fun from(brand: Brand) = BrandResult(
                id = brand.id,
                name = brand.name,
                createdAt = brand.createdAt,
                updatedAt = brand.updatedAt
            )
        }
    }
}