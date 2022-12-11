package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class LocalProductDataSourceImpl(private val productDao: ProductDao) : LocalProductDataSource {

    override fun getProducts(sectionId: Int): Flow<List<Product>> {
        return productDao.loadAllBySectionId(sectionId)
            .distinctUntilChanged()
            .map { it.map(ProductEntity::toProduct) }
    }

    override suspend fun saveProducts(sectionId: Int, products: List<Product>) {
        val entities = products.map { it.toEntity(sectionId) }
        productDao.insertAll(entities)
    }

    override suspend fun likeProduct(productId: Int) {
        productDao.updateLiked(id = productId, isLiked = true)
    }

    override suspend fun unlikeProduct(productId: Int) {
        productDao.updateLiked(id = productId, isLiked = false)
    }
}