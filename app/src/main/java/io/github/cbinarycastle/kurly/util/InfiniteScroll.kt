package io.github.cbinarycastle.kurly.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.infiniteScroll(
    prefetchDistance: Int = 1,
    loadMore: () -> Unit,
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager
            if (layoutManager !is LinearLayoutManager) {
                throw IllegalStateException("LayoutManager is must be LinearLayoutManager")
            }

            val adapter = recyclerView.adapter ?: throw IllegalStateException("Adapter is not set")
            if (adapter.itemCount == 0) {
                return
            }

            val prefetchAnchor = adapter.itemCount - 1 - prefetchDistance
            val shouldFetch = layoutManager.findLastVisibleItemPosition() >= prefetchAnchor

            if (shouldFetch) {
                loadMore()
            }
        }
    })
}