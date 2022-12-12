package io.github.cbinarycastle.kurly.ui.section

import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.*
import io.github.cbinarycastle.kurly.R
import io.github.cbinarycastle.kurly.databinding.ViewholderSectionBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.SectionType
import io.github.cbinarycastle.kurly.ui.model.SectionItem
import io.github.cbinarycastle.kurly.ui.product.GridProductAdapter
import io.github.cbinarycastle.kurly.ui.product.HorizontalProductAdapter
import io.github.cbinarycastle.kurly.ui.product.VerticalProductAdapter
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
    private val spacerItemDecorations: MutableList<SpacerItemDecoration> = mutableListOf()

    init {
        val itemAnimator = binding.productsRecyclerView.itemAnimator as? SimpleItemAnimator
        itemAnimator?.supportsChangeAnimations = false
    }

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

            spacerItemDecorations.forEach { removeItemDecoration(it) }
            sectionType.spacerItemDecorations(resources)
                .onEach { addItemDecoration(it) }
                .also {
                    with(spacerItemDecorations) {
                        clear()
                        addAll(it)
                    }
                }
        }
    }

    private fun collectProducts(flow: Flow<List<Product>>) {
        productsJob?.cancel()
        productsJob = viewLifecycle.coroutineScope.launch {
            flow.flowWithLifecycle(viewLifecycle).collect {
                adapter?.submitList(it) {
                    binding.shimmer.root.isVisible = it.isEmpty()
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
    SectionType.VERTICAL -> VerticalProductAdapter(toggleLikeProduct)
    SectionType.HORIZONTAL -> HorizontalProductAdapter(toggleLikeProduct)
    SectionType.GRID -> GridProductAdapter(toggleLikeProduct)
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

private fun SectionType.spacerItemDecorations(resources: Resources): List<SpacerItemDecoration> =
    when (this) {
        SectionType.VERTICAL -> {
            listOf(
                SpacerItemDecoration(
                    size = resources.getDimensionPixelSize(R.dimen.product_list_vertical_space),
                    orientation = SpacerItemDecoration.VERTICAL
                )
            )
        }
        SectionType.HORIZONTAL -> {
            listOf(
                SpacerItemDecoration(
                    size = resources.getDimensionPixelSize(R.dimen.product_list_horizontal_space)
                )
            )
        }
        SectionType.GRID -> {
            listOf(
                SpacerItemDecoration(
                    size = resources.getDimensionPixelSize(R.dimen.product_list_horizontal_space),
                    spanCount = GridSectionSpanCount
                ),
                SpacerItemDecoration(
                    size = resources.getDimensionPixelSize(R.dimen.product_list_vertical_space),
                    spanCount = GridSectionSpanCount,
                    orientation = SpacerItemDecoration.VERTICAL
                )
            )
        }
    }