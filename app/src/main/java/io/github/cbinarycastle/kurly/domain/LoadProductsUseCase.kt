package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.data.product.ProductRepository
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, List<Product>>(ioDispatcher) {

    override fun execute(params: Int): Flow<Result<List<Product>>> {
        return productRepository.loadProducts(sectionId = params)
            .map { Result.Success(it) }
    }
}