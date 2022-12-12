package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.ui.model.SectionItem

private val DiffCallback = object : DiffUtil.ItemCallback<SectionItem>() {
    override fun areItemsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean =
        oldItem.section.id == newItem.section.id

    override fun areContentsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean =
        oldItem.section == newItem.section
}

class SectionAdapter(
    private val toggleLikeProduct: (Product) -> Unit,
    private val viewLifecycle: Lifecycle,
) : ListAdapter<SectionItem, SectionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder.create(
            parent = parent,
            toggleLikeProduct = toggleLikeProduct,
            viewLifecycle = viewLifecycle
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val sectionItem = getItem(position)
        if (sectionItem != null) {
            holder.bind(sectionItem)
        }
    }

    override fun onViewRecycled(holder: SectionViewHolder) {
        holder.onViewRecycled()
    }
}