package com.frostdean.application.port.out

import com.frostdean.domain.Product


interface SaveProductPort {
    fun save(product: Product): Product
    fun deleteById(id: Long)
}