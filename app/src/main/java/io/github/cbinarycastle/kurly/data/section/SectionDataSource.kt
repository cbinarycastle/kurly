package io.github.cbinarycastle.kurly.data.section

import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Section

interface SectionDataSource {

    suspend fun getSections(page: Int): Page<Section>
}