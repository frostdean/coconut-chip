package com.frostdean.application.port.out

import com.frostdean.domain.Product


interface GetProductPort {
    fun findById(id: Long): Product?
    fun findByBrandId(brandId: Long): List<Product>
    fun findByCategoryId(categoryId: Long): List<Product>
    fun findAll(): List<Product>
}