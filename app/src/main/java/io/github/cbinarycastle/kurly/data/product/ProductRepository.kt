package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val localProductDataSource: LocalProductDataSource,
    private val remoteProductDataSource: RemoteProductDataSource,
) {
    fun getProducts(sectionId: Int): Flow<List<Product>> = flow {
        val products = remoteProductDataSource.getProducts(sectionId)
        localProductDataSource.saveProducts(sectionId, products)

        val savedProducts = localProductDataSource.getProducts(sectionId)
        emitAll(savedProducts)
    }

    suspend fun likeProduct(productId: Int) {
        localProductDataSource.likeProduct(productId)
    }

    suspend fun unlikeProduct(productId: Int) {
        localProductDataSource.unlikeProduct(productId)
    }
}