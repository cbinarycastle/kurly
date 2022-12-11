package io.github.cbinarycastle.kurly.ui.model

import io.github.cbinarycastle.kurly.domain.model.Result

enum class LoadState {
    NOT_LOADING, LOADING, ERROR
}

val Result<*>.loadState: LoadState
    get() = when (this) {
        is Result.Success<*> -> LoadState.NOT_LOADING
        is Result.Error -> LoadState.ERROR
        Result.Loading -> LoadState.LOADING
    }