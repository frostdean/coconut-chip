package com.frostdean.infrastructure.adapter

import com.frostdean.application.port.out.SaveProductPort
import com.frostdean.domain.Product
import com.frostdean.infrastructure.jpa.JpaProduct
import com.frostdean.infrastructure.jpa.JpaProductRepository
import org.springframework.stereotype.Component

@Component
class JpaSaveProductAdapter(
    private val jpaProductRepository: JpaProductRepository
) : SaveProductPort {
    override fun save(product: Product): Product {
        return jpaProductRepository.save(JpaProduct.from(product)).toDomain()
    }

    override fun deleteById(id: Long) {
        jpaProductRepository.deleteById(id)
    }

    override fun deleteByBrandId(brandId: Long) {
        jpaProductRepository.deleteByBrandId(brandId)
    }
}