package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderGridProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class GridProductViewHolder private constructor(
    private val binding: ViewholderGridProductBinding,
    toggleLike: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.content.heartImageButton.setOnClickListener {
            toggleLike(bindingAdapterPosition)
        }
    }

    fun bind(product: Product) {
        binding.content.product = product
        binding.content.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, toggleLike: (Int) -> Unit) =
            GridProductViewHolder(
                binding = ViewholderGridProductBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                toggleLike = toggleLike
            )
    }
}