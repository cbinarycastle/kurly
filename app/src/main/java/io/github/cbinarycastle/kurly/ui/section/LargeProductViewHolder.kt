package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderLargeProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class LargeProductViewHolder private constructor(
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

    companion object {
        fun create(parent: ViewGroup, toggleLikeProduct: (Int) -> Unit) =
            LargeProductViewHolder(
                binding = ViewholderLargeProductBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                toggleLikeProduct = toggleLikeProduct
            )
    }
}