package com.frostdean.application

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.application.port.out.GetProductPort
import com.frostdean.application.port.out.SaveProductPort
import org.springframework.stereotype.Service

@Service
class ProductUsecaseImpl(
    private val getProductPort: GetProductPort,
    private val saveProductPort: SaveProductPort
): ProductUsecase {
}