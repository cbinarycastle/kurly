package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val likeDataSource: LikeDataSource,
) {
    suspend fun getProducts(sectionId: Int): List<Product> =
        productDataSource.getProducts(sectionId)

    fun getLikedProductIds(productIds: List<Int>): Flow<List<Int>> =
        likeDataSource.getLikedProductIds(productIds)

    fun likeProduct(productId: Int) {
        likeDataSource.likeProduct(productId)
    }

    fun unlikeProduct(productId: Int) {
        likeDataSource.unlikeProduct(productId)
    }
}