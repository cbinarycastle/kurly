package io.github.cbinarycastle.kurly.ui.model

data class DataWithLoadState<T>(
    val data: T,
    val loadState: LoadState,
)