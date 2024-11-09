package com.frostdean.application.port.out

import com.frostdean.domain.Brand

interface GetBrandPort {
    fun findAll(): List<Brand>
    fun findById(id: Long): Brand?
    fun existsById(id: Long): Boolean
    fun existsByName(name: String): Boolean
}