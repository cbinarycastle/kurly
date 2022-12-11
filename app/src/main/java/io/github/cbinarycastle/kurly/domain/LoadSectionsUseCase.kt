package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.data.product.ProductRepository
import io.github.cbinarycastle.kurly.data.section.SectionRepository
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.util.combineAll
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadSectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val productRepository: ProductRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, Page<Section>>(ioDispatcher) {

    override fun execute(params: Int): Flow<Result<Page<Section>>> = flow {
        emit(Result.Loading)

        val sectionPage = sectionRepository.getSections(page = params)

        val sectionPageWithProductFlow = sectionPage.data.map { it.toFlowWithProducts() }
            .combineAll()
            .map { Result.Success(sectionPage.copy(data = it)) }

        emitAll(sectionPageWithProductFlow)
    }

    private fun Section.toFlowWithProducts(): Flow<Section> {
        return productRepository.getProducts(sectionId = this.id)
            .map { this.copy(products = it) }
    }
}