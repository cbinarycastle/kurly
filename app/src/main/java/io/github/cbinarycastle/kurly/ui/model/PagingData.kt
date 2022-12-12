package io.github.cbinarycastle.kurly.ui.model

import io.github.cbinarycastle.kurly.domain.model.Page

class PagingData<T> {

    private val pages = mutableListOf<Page<T>>()

    val items: List<T>
        get() = pages.flatMap { it.data }

    fun updatePage(page: Page<T>) {
        val existingPageIndex = pages.indexOfFirst { it.currentPage == page.currentPage }
        if (existingPageIndex >= 0) {
            pages[existingPageIndex] = page
        } else {
            pages.add(page)
        }
    }

    fun clear() {
        pages.clear()
    }
}