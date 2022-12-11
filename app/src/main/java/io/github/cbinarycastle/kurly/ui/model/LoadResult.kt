package io.github.cbinarycastle.kurly.ui.model

data class LoadResult<T>(
    val data: T,
    val loadStates: LoadStates,
)