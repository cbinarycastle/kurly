package io.github.cbinarycastle.kurly.domain.model

data class Page<T>(
    val data: List<T>,
    val nextPage: Int?,
) {
    val hasNext: Boolean
        get() = nextPage != null
}