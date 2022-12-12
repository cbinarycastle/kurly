package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.data.section.SectionRepository
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadSectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, Page<Section>>(ioDispatcher) {

    override fun execute(params: Int): Flow<Result<Page<Section>>> = flow {
        emit(Result.Loading)

        val sections = sectionRepository.getSections(page = params)
        emit(Result.Success(sections))
    }
}