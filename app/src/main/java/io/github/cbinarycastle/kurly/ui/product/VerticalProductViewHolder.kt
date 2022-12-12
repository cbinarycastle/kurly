package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderVerticalProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class VerticalProductViewHolder private constructor(
    private val binding: ViewholderVerticalProductBinding,
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

    companion object {
        fun create(parent: ViewGroup, toggleLikeProduct: (Int) -> Unit) =
            VerticalProductViewHolder(
                binding = ViewholderVerticalProductBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                toggleLikeProduct = toggleLikeProduct
            )
    }
}