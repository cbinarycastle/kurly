package io.github.cbinarycastle.kurly.ui.model

import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.Section
import kotlinx.coroutines.flow.Flow

class SectionItem(
    val section: Section,
    val productsFlow: Flow<List<Product>>,
)