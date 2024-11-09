package com.frostdean.interfaces.controllers

import com.frostdean.application.port.`in`.BrandUsecase
import com.frostdean.interfaces.common.CoconutResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/brands")
class BrandController(
    private val brandUsecase: BrandUsecase
) {

    // 구현 4 API
    @PostMapping
    fun createBrand(@RequestBody request: BrandCreateRequest): CoconutResponse<BrandResponse> {
        val command = BrandUsecase.BrandCreateCommand(
            name = request.name
        )
        return brandUsecase.createBrand(command)
            .let { BrandResponse(brandId = it.id, brandName = it.name) }
            .let { CoconutResponse.success(it) }
    }

    // 구현 4 API
    @PatchMapping("/{brandId}")
    fun updateBrand(
        @PathVariable brandId: Long, @RequestBody request: BrandUpdateRequest
    ): CoconutResponse<BrandResponse> {
        val command = BrandUsecase.BrandUpdateCommand(
            brandId = brandId,
            name = request.name
        )
        return brandUsecase.updateBrand(command)
            .let { BrandResponse(brandId = it.id, brandName = it.name) }
            .let { CoconutResponse.success(it) }
    }

    // 구현 4 API
    @DeleteMapping("/{brandId}")
    fun deleteBrand(@PathVariable brandId: Long): CoconutResponse<Unit> {
        brandUsecase.deleteBrand(brandId)
        return CoconutResponse.emptyOk()
    }

    data class BrandUpdateRequest(val name: String)
    data class BrandCreateRequest(val name: String)
    data class BrandResponse(val brandId: Long, val brandName: String)
}