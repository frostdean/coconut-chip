package com.frostdean.application.port.out

import com.frostdean.domain.Brand

interface SaveBrandPort {
    fun save(brand: Brand): Brand
    fun deleteById(id: Long)
}