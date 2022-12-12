package io.github.cbinarycastle.kurly.ui.section

import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderSmallProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product

class SmallProductViewHolder(
    private val binding: ViewholderSmallProductBinding,
    toggleLike: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.heartImageButton.setOnClickListener {
            toggleLike(bindingAdapterPosition)
        }
    }

    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}