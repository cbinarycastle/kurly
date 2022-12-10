package io.github.cbinarycastle.kurly.domain.model

data class Section(
    val id: Int,
    val title: String,
    val type: SectionType,
    val products: List<Product> = emptyList(),
)