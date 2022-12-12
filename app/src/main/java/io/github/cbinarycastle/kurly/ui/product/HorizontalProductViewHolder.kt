package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderHorizontalProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class HorizontalProductViewHolder private constructor(
    private val binding: ViewholderHorizontalProductBinding,
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

    companion object {
        fun create(parent: ViewGroup, toggleLike: (Int) -> Unit) =
            HorizontalProductViewHolder(
                binding = ViewholderHorizontalProductBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                toggleLike = toggleLike
            )
    }
}