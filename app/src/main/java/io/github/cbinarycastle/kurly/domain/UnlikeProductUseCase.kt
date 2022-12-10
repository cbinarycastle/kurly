package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.data.product.ProductRepository
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UnlikeProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<Product, Unit>(ioDispatcher) {

    override suspend fun execute(params: Product) {
        productRepository.unlikeProduct(productId = params.id)
    }
}