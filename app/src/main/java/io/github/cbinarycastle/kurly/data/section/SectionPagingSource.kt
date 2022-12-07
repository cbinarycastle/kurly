package io.github.cbinarycastle.kurly.data.section

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.domain.model.Section
import javax.inject.Inject

private const val INITIAL_PAGE = 1

class SectionPagingSource @Inject constructor(
    private val kurlyService: KurlyService,
) : PagingSource<Int, Section>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> = try {
        val page = params.key ?: INITIAL_PAGE
        val response = kurlyService.getSections(page)

        LoadResult.Page(
            data = response.data.map(SectionInfo::toSection),
            prevKey = null,
            nextKey = response.paging?.nextPage
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Section>): Int? = null
}