package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.databinding.ViewholderSectionBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.util.layoutInflater

private val DiffCallback = object : DiffUtil.ItemCallback<Section>() {
    override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean =
        oldItem == newItem
}

class SectionAdapter(private val toggleLikeProduct: (Product) -> Unit) :
    ListAdapter<Section, SectionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            binding = ViewholderSectionBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            ),
            toggleLikeProduct = toggleLikeProduct
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = getItem(position)
        if (section != null) {
            holder.bind(section)
        }
    }
}