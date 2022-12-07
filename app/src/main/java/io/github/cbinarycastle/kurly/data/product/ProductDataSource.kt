package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.domain.model.Product

interface ProductDataSource {

    suspend fun getProducts(sectionId: Int): List<Product>
}