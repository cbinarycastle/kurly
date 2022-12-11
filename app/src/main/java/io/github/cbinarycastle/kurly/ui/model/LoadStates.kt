package io.github.cbinarycastle.kurly.ui.model

data class LoadStates(
    val refresh: LoadState,
    val append: LoadState
) {
    companion object {
        val IDLE = LoadStates(
            refresh = LoadState.NOT_LOADING,
            append = LoadState.NOT_LOADING
        )
    }
}