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

        with(outRect) {
            when (orientation) {
                HORIZONTAL -> {
                    val baseSpanCount = if (spanCount > 1) spanCount else itemCount
                    val isFirstLine = position % baseSpanCount == 0
                    val isLastLine = position % baseSpanCount == baseSpanCount - 1

                    left = halfOfSize
                    right = halfOfSize
                    if (isFirstLine) {
                        left = 0
                    }
                    if (isLastLine) {
                        right = 0
                    }
                }
                VERTICAL -> {
                    val isFirstLine = position / spanCount == 0
                    val isLastLine = position / spanCount == itemCount / spanCount - 1

                    top = halfOfSize
                    bottom = halfOfSize
                    if (isFirstLine) {
                        top = 0
                    }
                    if (isLastLine) {
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