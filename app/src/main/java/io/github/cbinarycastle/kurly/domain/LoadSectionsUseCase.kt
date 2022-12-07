package io.github.cbinarycastle.kurly.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.cbinarycastle.kurly.data.section.SectionPagingSource
import io.github.cbinarycastle.kurly.di.IoDispatcher
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadSectionsUseCase @Inject constructor(
    private val sectionPagingSource: SectionPagingSource,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, PagingData<Section>>(ioDispatcher) {

    override fun execute(params: Int): Flow<Result<PagingData<Section>>> {
        return Pager(
            config = PagingConfig(pageSize = 4),
            pagingSourceFactory = { sectionPagingSource }
        ).flow.map { Result.Success(it) }
    }
}