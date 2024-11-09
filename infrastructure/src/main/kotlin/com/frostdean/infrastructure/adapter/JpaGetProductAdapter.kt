package com.frostdean.infrastructure.adapter

import com.frostdean.application.port.out.GetProductPort
import com.frostdean.domain.Product
import com.frostdean.infrastructure.jpa.JpaProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class JpaGetProductAdapter(
    private val jpaProductRepository: JpaProductRepository
) : GetProductPort {
    override fun findById(id: Long): Product? {
        return jpaProductRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByBrandId(brandId: Long): List<Product> {
        return jpaProductRepository.findByBrandId(brandId).map { it.toDomain() }
    }

    override fun findByCategoryId(categoryId: Long): List<Product> {
        return jpaProductRepository.findByCategoryId(categoryId).map { it.toDomain() }
    }

    override fun findAll(): List<Product> {
        return jpaProductRepository.findAll().map { it.toDomain() }
    }
}

