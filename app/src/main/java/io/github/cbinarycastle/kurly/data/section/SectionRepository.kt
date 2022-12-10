package io.github.cbinarycastle.kurly.data.section

import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Section
import javax.inject.Inject

class SectionRepository @Inject constructor(private val sectionDataSource: SectionDataSource) {

    suspend fun getSections(page: Int): Page<Section> = sectionDataSource.getSections(page)
}