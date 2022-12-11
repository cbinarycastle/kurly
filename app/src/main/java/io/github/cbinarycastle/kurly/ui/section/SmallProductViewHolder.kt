package io.github.cbinarycastle.kurly.ui.section

import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderSmallProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product

class SmallProductViewHolder(
    private val binding: ViewholderSmallProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}