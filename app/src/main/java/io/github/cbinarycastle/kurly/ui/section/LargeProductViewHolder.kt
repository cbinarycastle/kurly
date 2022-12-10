package io.github.cbinarycastle.kurly.ui.section

import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderLargeProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product

class LargeProductViewHolder(
    private val binding: ViewholderLargeProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.product = product
    }
}