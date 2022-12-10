package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product

interface RemoteProductDataSource {

    suspend fun getProducts(sectionId: Int): List<Product>
}