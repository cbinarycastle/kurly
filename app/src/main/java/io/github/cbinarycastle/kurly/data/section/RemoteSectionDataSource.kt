package io.github.cbinarycastle.kurly.data.section

import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Section

class RemoteSectionDataSource(private val kurlyService: KurlyService) : SectionDataSource {

    override suspend fun getSections(page: Int): Page<Section> {
        return kurlyService.getSections(page).toSectionPage(page)
    }
}