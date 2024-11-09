package com.frostdean.infrastructure.adapter

import com.frostdean.application.port.out.GetBrandPort
import com.frostdean.domain.Brand
import com.frostdean.infrastructure.jpa.JpaBrandRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class JpaGetBrandAdapter(
    private val jpaBrandRepository: JpaBrandRepository
) : GetBrandPort{
    override fun findAll(): List<Brand> {
        return jpaBrandRepository.findAll().map { it.toDomain() }
    }

    override fun findById(id: Long): Brand? {
        return jpaBrandRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun existsById(id: Long): Boolean {
        return jpaBrandRepository.existsById(id)
    }

    override fun existsByName(name: String): Boolean {
        return jpaBrandRepository.existsByName(name)
    }
}