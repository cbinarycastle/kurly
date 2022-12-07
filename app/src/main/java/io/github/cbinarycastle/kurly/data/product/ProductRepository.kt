package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource,
) {
    suspend fun getProducts(sectionId: Int): List<Product> =
        productDataSource.getProducts(sectionId)
}