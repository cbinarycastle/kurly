package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface LocalProductDataSource {

    fun getProducts(sectionId: Int): Flow<List<Product>>

    suspend fun saveProducts(sectionId: Int, products: List<Product>)

    suspend fun likeProduct(productId: Int)

    suspend fun unlikeProduct(productId: Int)
}