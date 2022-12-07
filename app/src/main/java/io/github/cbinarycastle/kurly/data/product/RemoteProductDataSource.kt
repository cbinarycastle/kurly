package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.domain.model.Product

class RemoteProductDataSource(private val kurlyService: KurlyService) : ProductDataSource {

    override suspend fun getProducts(sectionId: Int): List<Product> {
        return kurlyService.getProducts(sectionId)
            .data
            .map(ProductInfo::toProduct)
    }
}