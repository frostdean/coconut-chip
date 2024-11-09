package com.frostdean.application

import com.frostdean.domain.Brand
import com.frostdean.domain.Category
import com.frostdean.domain.Product
import java.math.BigDecimal
import java.time.LocalDateTime

val testBrands = listOf(
    Brand(1, "A", LocalDateTime.now(), LocalDateTime.now()),
    Brand(2, "B", LocalDateTime.now(), LocalDateTime.now()),
    Brand(3, "C", LocalDateTime.now(), LocalDateTime.now())
)

val testCategories = listOf(
    Category(1, "상의", LocalDateTime.now(), LocalDateTime.now()),
    Category(2, "아우터", LocalDateTime.now(), LocalDateTime.now()),
    Category(3, "바지", LocalDateTime.now(), LocalDateTime.now()),
    Category(4, "스니커즈", LocalDateTime.now(), LocalDateTime.now()),
    Category(5, "가방", LocalDateTime.now(), LocalDateTime.now()),
    Category(6, "모자", LocalDateTime.now(), LocalDateTime.now()),
    Category(7, "양말", LocalDateTime.now(), LocalDateTime.now()),
    Category(8, "액세서리", LocalDateTime.now(), LocalDateTime.now()),
)


val testProducts = listOf(
    Product(1, 1, 1, "A_상의", BigDecimal(10000), LocalDateTime.now(), LocalDateTime.now()),
    Product(2, 1, 2, "B_상의", BigDecimal(11000), LocalDateTime.now(), LocalDateTime.now()),
    Product(3, 1, 3, "C_상의", BigDecimal(19000), LocalDateTime.now(), LocalDateTime.now()),

    Product(4, 2, 1, "A_아우터", BigDecimal(24000), LocalDateTime.now(), LocalDateTime.now()),
    Product(5, 2, 2, "B_아우터", BigDecimal(15000), LocalDateTime.now(), LocalDateTime.now()),
    Product(6, 2, 3, "C_아우터", BigDecimal(9000), LocalDateTime.now(), LocalDateTime.now()),

    Product(7, 3, 1, "A_바지", BigDecimal(7000), LocalDateTime.now(), LocalDateTime.now()),
    Product(8, 3, 2, "B_바지", BigDecimal(8000), LocalDateTime.now(), LocalDateTime.now()),
    Product(9, 3, 3, "C_바지", BigDecimal(9000), LocalDateTime.now(), LocalDateTime.now()),

    Product(10, 4, 1, "A_스니커즈", BigDecimal(14000), LocalDateTime.now(), LocalDateTime.now()),
    Product(11, 4, 2, "B_스니커즈", BigDecimal(17000), LocalDateTime.now(), LocalDateTime.now()),
    Product(12, 4, 3, "C_스니커즈", BigDecimal(12000), LocalDateTime.now(), LocalDateTime.now()),

    Product(13, 5, 1, "A_가방", BigDecimal(23000), LocalDateTime.now(), LocalDateTime.now()),
    Product(14, 5, 2, "B_가방", BigDecimal(14000), LocalDateTime.now(), LocalDateTime.now()),
    Product(15, 5, 3, "C_가방", BigDecimal(25000), LocalDateTime.now(), LocalDateTime.now()),

    Product(16, 6, 1, "A_모자", BigDecimal(26000), LocalDateTime.now(), LocalDateTime.now()),
    Product(17, 6, 2, "B_모자", BigDecimal(17800), LocalDateTime.now(), LocalDateTime.now()),
    Product(18, 6, 3, "C_모자", BigDecimal(18000), LocalDateTime.now(), LocalDateTime.now()),

    Product(19, 7, 1, "A_양말", BigDecimal(21900), LocalDateTime.now(), LocalDateTime.now()),
    Product(20, 7, 2, "B_양말", BigDecimal(20000), LocalDateTime.now(), LocalDateTime.now()),
    Product(21, 7, 3, "C_양말", BigDecimal(21100), LocalDateTime.now(), LocalDateTime.now()),

    Product(22, 8, 1, "A_액세서리", BigDecimal(8900), LocalDateTime.now(), LocalDateTime.now()),
    Product(23, 8, 2, "B_액세서리", BigDecimal(7000), LocalDateTime.now(), LocalDateTime.now()),
    Product(24, 8, 3, "C_액세서리", BigDecimal(7000), LocalDateTime.now(), LocalDateTime.now())
)