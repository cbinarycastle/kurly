package io.github.cbinarycastle.kurly.data.product

import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.domain.model.Product

class RemoteProductDataSourceImpl(
    private val kurlyService: KurlyService,
) : RemoteProductDataSource {

    override suspend fun getProducts(sectionId: Int): List<Product> {
        return kurlyService.getProducts(sectionId)
            .data
            .map(ProductInfo::toProduct)
    }
}