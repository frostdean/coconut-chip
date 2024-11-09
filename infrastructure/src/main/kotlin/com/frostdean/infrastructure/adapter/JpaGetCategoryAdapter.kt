package com.frostdean.infrastructure.adapter

import com.frostdean.application.port.out.GetCategoryPort
import com.frostdean.domain.Category
import com.frostdean.infrastructure.jpa.JpaCategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class JpaGetCategoryAdapter(
    private val jpaCategoryRepository: JpaCategoryRepository
) : GetCategoryPort {
    override fun findById(id: Long): Category? {
        return jpaCategoryRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun existsById(id: Long): Boolean {
        return jpaCategoryRepository.existsById(id)
    }

    override fun findAll(): List<Category> {
        return jpaCategoryRepository.findAll().map { it.toDomain() }
    }

}