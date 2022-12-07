package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.data.product.ProductRepository
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class LoadProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, List<Product>>(ioDispatcher) {

    override fun execute(params: Int): Flow<Result<List<Product>>> {
        return loadProducts(sectionId = params).flatMapLatest { products ->
            loadLikedProductIds(products).map { likedProductIds ->
                products
                    .map { product ->
                        product.copy(isLiked = likedProductIds.contains(product.id))
                    }
                    .let { Result.Success(it) }
            }
        }
    }

    private fun loadProducts(sectionId: Int): Flow<List<Product>> = flow {
        val products = productRepository.getProducts(sectionId)
        emit(products)
    }

    private fun loadLikedProductIds(products: List<Product>): Flow<List<Int>> {
        val productIds = products.map { it.id }
        return productRepository.getLikedProductIds(productIds)
    }
}