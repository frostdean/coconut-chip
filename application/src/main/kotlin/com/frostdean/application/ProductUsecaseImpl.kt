package com.frostdean.application

import com.frostdean.application.port.`in`.ProductUsecase
import com.frostdean.application.port.out.GetBrandPort
import com.frostdean.application.port.out.GetCategoryPort
import com.frostdean.application.port.out.GetProductPort
import com.frostdean.application.port.out.SaveProductPort
import com.frostdean.domain.Brand
import com.frostdean.domain.Category
import com.frostdean.domain.Product
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class ProductUsecaseImpl(
    private val getProductPort: GetProductPort,
    private val getBrandPort: GetBrandPort,
    private val getCategoryPort: GetCategoryPort,
    private val saveProductPort: SaveProductPort
) : ProductUsecase {

    @Transactional
    override fun createProduct(command: ProductUsecase.ProductCreateCommand): ProductUsecase.ProductResult {
        if (getCategoryPort.existsById(command.categoryId).not())
            throw IllegalArgumentException("Category ${command.categoryId} was not found")
        if (getBrandPort.existsById(command.brandId).not())
            throw IllegalArgumentException("Brand ${command.brandId} was not found")

        return Product.create(
            categoryId = command.categoryId,
            brandId = command.brandId,
            name = command.name,
            price = command.price
        ).let { saveProductPort.save(it) }
            .let { ProductUsecase.ProductResult.from(it) }
    }

    @Transactional
    override fun updateProduct(command: ProductUsecase.ProductUpdateCommand): ProductUsecase.ProductResult {
        command.categoryId?.let {
            if (getCategoryPort.existsById(it).not())
                throw IllegalArgumentException("Category $it was not found")
        }
        command.brandId?.let {
            if (getBrandPort.existsById(it).not())
                throw IllegalArgumentException("Brand $it was not found")
        }

        val product = getProductPort.findById(command.productId)
            ?: throw IllegalArgumentException("Product ${command.productId} was not found")

        return product.update(
            categoryId = command.categoryId,
            brandId = command.brandId,
            name = command.name,
            price = command.price
        ).let { saveProductPort.save(it) }
            .let { ProductUsecase.ProductResult.from(it) }
    }

    @Transactional
    override fun deleteProduct(productId: Long) {
        val product =
            getProductPort.findById(productId) ?: throw IllegalArgumentException("Product $productId was not found")

        val brandCategory = getProductPort.findByCategoryIdAndBrandId(product.categoryId, product.brandId)
        if (brandCategory.size < 2) {
            throw RuntimeException("해당 브랜드의 카테고리 상품이 최소 1개는 존재해야 합니다")
        }

        saveProductPort.deleteById(product.id)

    }

    override fun getCheapestCoordiByCategory(): List<ProductDetail> {
        val allCategories = getCategoryPort.findAll().associateBy { it.id }
        val allBrands = getBrandPort.findAll().associateBy { it.id }
        return getProductPort.findAll()
            .groupBy { it.categoryId }
            .mapValues { it.value.minBy { product -> product.price } }
            .map { (categoryId, product) ->
                ProductDetail(
                    id = product.id,
                    categoryId = categoryId,
                    categoryName = allCategories[categoryId]?.name
                        ?: throw IllegalArgumentException("Category $categoryId was not found"),
                    brandId = product.brandId,
                    brandName = allBrands[product.brandId]?.name
                        ?: throw IllegalArgumentException("Brand ${product.brandId} was not found"),
                    name = product.name,
                    price = product.price,
                    createdAt = product.createdAt,
                    updatedAt = product.updatedAt
                )
            }
    }

    override fun getCheapestCoordiByBrand(): SingleBrandCheapestCoordi {
        val allBrands = getBrandPort.findAll()
        val allCategories = getCategoryPort.findAll().associateBy { it.id }

        return allBrands.map { getSingleBrandCheapestCoordi(it, allCategories) }
            .minBy { it.productList.sumOf { product -> product.price } }
    }

    override fun getCheapestAndMostExpensiveProductByCategory(categoryId: Long): CheapestAndMostExpensive {
        if (getCategoryPort.existsById(categoryId).not()) {
            throw IllegalArgumentException("Category $categoryId was not found")
        }

        val products = getProductPort.findByCategoryId(categoryId)
        val cheapestProduct = products.minBy { it.price }.let {
            ProductDetail(
                id = it.id,
                categoryId = it.categoryId,
                categoryName = getCategoryPort.findById(it.categoryId)?.name
                    ?: throw IllegalArgumentException("Category ${it.categoryId} was not found"),
                brandId = it.brandId,
                brandName = getBrandPort.findById(it.brandId)?.name
                    ?: throw IllegalArgumentException("Brand ${it.brandId} was not found"),
                name = it.name,
                price = it.price,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
        val mostExpensiveProduct = products.maxBy { it.price }.let {
            ProductDetail(
                id = it.id,
                categoryId = it.categoryId,
                categoryName = getCategoryPort.findById(it.categoryId)?.name
                    ?: throw IllegalArgumentException("Category ${it.categoryId} was not found"),
                brandId = it.brandId,
                brandName = getBrandPort.findById(it.brandId)?.name
                    ?: throw IllegalArgumentException("Brand ${it.brandId} was not found"),
                name = it.name,
                price = it.price,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

        return CheapestAndMostExpensive(
            cheapest = cheapestProduct,
            mostExpensive = mostExpensiveProduct
        )
    }

    data class CheapestAndMostExpensive(
        val cheapest: ProductDetail,
        val mostExpensive: ProductDetail
    )

    private fun getSingleBrandCheapestCoordi(
        brand: Brand,
        categoryMap: Map<Long, Category>,
    ): SingleBrandCheapestCoordi {
        val brandProducts = getProductPort.findByBrandId(brand.id)
        val cheapestProducts = brandProducts.groupBy { it.categoryId }
            .mapValues { it.value.minBy { product -> product.price } }
            .values.toList()

        return SingleBrandCheapestCoordi(
            brandId = brand.id,
            brandName = brand.name,
            productList = cheapestProducts.map {
                ProductDetail(
                    id = it.id,
                    categoryId = it.categoryId,
                    categoryName = categoryMap[it.categoryId]?.name
                        ?: throw IllegalArgumentException("Category ${it.categoryId} was not found"),
                    brandId = it.brandId,
                    brandName = brand.name,
                    name = it.name,
                    price = it.price,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }
        )

    }

    data class SingleBrandCheapestCoordi(
        val brandId: Long,
        val brandName: String,
        val productList: List<ProductDetail>
    )

    data class ProductDetail(
        val id: Long,
        val categoryId: Long,
        val categoryName: String,
        val brandId: Long,
        val brandName: String,
        val name: String,
        val price: BigDecimal,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )

}