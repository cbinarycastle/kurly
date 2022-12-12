package io.github.cbinarycastle.kurly.ui.section

import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.R
import io.github.cbinarycastle.kurly.databinding.ViewholderSectionBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.SectionType
import io.github.cbinarycastle.kurly.ui.model.SectionItem
import io.github.cbinarycastle.kurly.util.layoutInflater
import io.github.cbinarycastle.kurly.widget.SpacerItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private typealias ProductAdapter = ListAdapter<Product, out RecyclerView.ViewHolder>

private const val GridSectionSpanCount = 3

class SectionViewHolder private constructor(
    private val binding: ViewholderSectionBinding,
    private val toggleLikeProduct: (Product) -> Unit,
    private val viewLifecycle: Lifecycle,
) : RecyclerView.ViewHolder(binding.root) {

    private var productsJob: Job? = null
    private var adapter: ProductAdapter? = null
    private var spacerItemDecoration: SpacerItemDecoration? = null

    fun bind(item: SectionItem) {
        val section = item.section
        binding.section = section

        setupRecyclerView(section.type)
        collectProducts(item.productsFlow)
    }

    private fun setupRecyclerView(sectionType: SectionType) {
        adapter = sectionType.adapter(toggleLikeProduct)

        with(binding.productsRecyclerView) {
            adapter = this@SectionViewHolder.adapter
            layoutManager = sectionType.layoutManager(context)

            spacerItemDecoration?.let { removeItemDecoration(it) }
            spacerItemDecoration = sectionType.spacerItemDecoration(resources)
                .also { addItemDecoration(it) }
        }
    }

    private fun collectProducts(flow: Flow<List<Product>>) {
        productsJob?.cancel()
        productsJob = viewLifecycle.coroutineScope.launch {
            flow.flowWithLifecycle(viewLifecycle).collect {
                adapter?.submitList(it) {
                    binding.shimmer.isVisible = it.isEmpty()
                }
            }
        }
    }

    fun onViewRecycled() {
        productsJob?.cancel()
        productsJob = null
    }

    companion object {
        fun create(
            parent: ViewGroup,
            toggleLikeProduct: (Product) -> Unit,
            viewLifecycle: Lifecycle,
        ) = SectionViewHolder(
            binding = ViewholderSectionBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            ),
            toggleLikeProduct = toggleLikeProduct,
            viewLifecycle = viewLifecycle
        )
    }
}

private fun SectionType.adapter(
    toggleLikeProduct: (Product) -> Unit,
): ProductAdapter = when (this) {
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