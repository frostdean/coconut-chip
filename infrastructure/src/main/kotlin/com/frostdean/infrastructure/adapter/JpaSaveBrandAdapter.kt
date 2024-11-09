package com.frostdean.infrastructure.adapter

import com.frostdean.application.port.out.SaveBrandPort
import com.frostdean.domain.Brand
import com.frostdean.infrastructure.jpa.JpaBrand
import com.frostdean.infrastructure.jpa.JpaBrandRepository
import org.springframework.stereotype.Component

@Component
class JpaSaveBrandAdapter(
    private val jpaBrandRepository: JpaBrandRepository
) : SaveBrandPort {
    override fun save(brand: Brand): Brand {
        return jpaBrandRepository.save(JpaBrand.from(brand)).toDomain()
    }

    override fun deleteById(id: Long) {
        return jpaBrandRepository.deleteById(id)
    }
}