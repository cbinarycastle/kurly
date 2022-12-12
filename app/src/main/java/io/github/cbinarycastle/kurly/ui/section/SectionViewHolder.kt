package io.github.cbinarycastle.kurly.ui.section

import android.content.Context
import android.content.res.Resources
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.R
import io.github.cbinarycastle.kurly.databinding.ViewholderSectionBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.domain.model.SectionType
import io.github.cbinarycastle.kurly.widget.SpacerItemDecoration

private const val GridSectionSpanCount = 3

class SectionViewHolder(
    private val binding: ViewholderSectionBinding,
    private val toggleLikeProduct: (Product) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var spacerItemDecoration: SpacerItemDecoration? = null

    fun bind(section: Section) {
        binding.section = section

        with(binding.productsRecyclerView) {
            val sectionType = section.type

            adapter = sectionType.adapter(toggleLikeProduct).apply {
                submitList(section.products)
            }

            spacerItemDecoration?.let { removeItemDecoration(it) }
            spacerItemDecoration = sectionType.spacerItemDecoration(resources)
                .also { addItemDecoration(it) }
        }
    }
}

private fun SectionType.adapter(
    toggleLikeProduct: (Product) -> Unit,
): ListAdapter<Product, out RecyclerView.ViewHolder> = when (this) {
    SectionType.VERTICAL -> LargeProductAdapter(toggleLikeProduct)
    SectionType.HORIZONTAL, SectionType.GRID -> SmallProductAdapter(toggleLikeProduct)
}

private fun SectionType.layoutManager(context: Context) = when (this) {
    SectionType.VERTICAL -> LinearLayoutManager(context)
    SectionType.HORIZONTAL -> {
        LinearLayoutManager(context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
    }
    SectionType.GRID -> GridLayoutManager(context, GridSectionSpanCount)
}

private fun SectionType.spacerItemDecoration(resources: Resources) = when (this) {
    SectionType.VERTICAL -> {
        SpacerItemDecoration(
            size = resources.getDimensionPixelSize(R.dimen.product_list_space),
            orientation = SpacerItemDecoration.VERTICAL
        )
    }
    SectionType.HORIZONTAL -> {
        SpacerItemDecoration(
            size = resources.getDimensionPixelSize(R.dimen.product_list_space)
        )
    }
    SectionType.GRID -> {
        SpacerItemDecoration(
            size = resources.getDimensionPixelSize(R.dimen.product_list_space),
            spanCount = GridSectionSpanCount
        )
    }
}

@BindingAdapter("sectionType")
fun setLayoutManager(recyclerView: RecyclerView, sectionType: SectionType) {
    with(recyclerView) {
        layoutManager = sectionType.layoutManager(context)
    }
}