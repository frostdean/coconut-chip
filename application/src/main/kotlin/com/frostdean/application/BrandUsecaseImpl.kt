package com.frostdean.application

import com.frostdean.application.port.`in`.BrandUsecase
import com.frostdean.application.port.out.GetBrandPort
import com.frostdean.application.port.out.SaveBrandPort
import com.frostdean.application.port.out.SaveProductPort
import com.frostdean.domain.Brand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandUsecaseImpl(
    private val getBrandPort: GetBrandPort,
    private val saveBrandPort: SaveBrandPort,
    private val saveProductPort: SaveProductPort
) : BrandUsecase {
    @Transactional
    override fun createBrand(command: BrandUsecase.BrandCreateCommand): BrandUsecase.BrandResult {
        val duplicated = getBrandPort.existsByName(command.name)
        if (duplicated)
            throw IllegalArgumentException("Brand ${command.name} already exists")

        return Brand.create(command.name)
            .let { saveBrandPort.save(it) }
            .let { BrandUsecase.BrandResult.from(it) }
    }

    @Transactional
    override fun updateBrand(command: BrandUsecase.BrandUpdateCommand): BrandUsecase.BrandResult {
        val brand = getBrandPort.findById(command.brandId)
            ?: throw IllegalArgumentException("Brand ${command.brandId} was not found")

        val duplicated = getBrandPort.existsByName(command.name)
        if (duplicated)
            throw IllegalArgumentException("Brand ${command.name} already exists")

        return brand.update(command.name)
            .let { saveBrandPort.save(it) }
            .let { BrandUsecase.BrandResult.from(it) }
    }

    @Transactional
    override fun deleteBrand(brandId: Long) {
        val brand = getBrandPort.findById(brandId)
            ?: throw IllegalArgumentException("Brand $brandId was not found")

        saveBrandPort.deleteById(brand.id)
        saveProductPort.deleteByBrandId(brand.id)
    }
}