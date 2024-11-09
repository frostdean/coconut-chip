package com.frostdean.application.port.out

import com.frostdean.domain.Category

interface GetCategoryPort {
    fun findById(id: Long): Category?
    fun existsById(id: Long): Boolean
    fun findAll(): List<Category>
}