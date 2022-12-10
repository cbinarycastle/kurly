package io.github.cbinarycastle.kurly.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation

class SpacerItemDecoration(
    size: Int,
    private val spanCount: Int = 1,
    @Orientation private val orientation: Int = HORIZONTAL,
) : RecyclerView.ItemDecoration() {

    private val halfOfSize = size / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        val baseSpanCount = if (spanCount > 1) spanCount else itemCount

        val isFirstItem = position % baseSpanCount == 0
        val isLastItem = position % baseSpanCount == baseSpanCount - 1

        with(outRect) {
            when (orientation) {
                HORIZONTAL -> {
                    left = halfOfSize
                    right = halfOfSize
                    if (isFirstItem) {
                        left = 0
                    }
                    if (isLastItem) {
                        right = 0
                    }
                }
                VERTICAL -> {
                    top = halfOfSize
                    bottom = halfOfSize
                    if (isFirstItem) {
                        top = 0
                    }
                    if (isLastItem) {
                        bottom = 0
                    }
                }
            }
        }
    }

    companion object {
        const val HORIZONTAL = RecyclerView.HORIZONTAL
        const val VERTICAL = RecyclerView.VERTICAL
    }
}