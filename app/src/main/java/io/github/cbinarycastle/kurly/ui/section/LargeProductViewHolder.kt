package io.github.cbinarycastle.kurly.ui.section

import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderLargeProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product

class LargeProductViewHolder(
    private val binding: ViewholderLargeProductBinding,
    toggleLikeProduct: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.heartImageButton.setOnClickListener {
            toggleLikeProduct(bindingAdapterPosition)
        }
    }

    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}