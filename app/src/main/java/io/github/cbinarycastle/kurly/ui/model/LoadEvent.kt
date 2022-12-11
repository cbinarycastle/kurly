package io.github.cbinarycastle.kurly.ui.model

sealed class LoadEvent(val page: Int) {

    object Refresh : LoadEvent(1)
    class Append(page: Int) : LoadEvent(page)
}