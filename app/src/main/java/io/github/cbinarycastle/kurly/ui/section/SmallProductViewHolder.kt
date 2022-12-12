package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderSmallProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class SmallProductViewHolder private constructor(
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

    companion object {
        fun create(parent: ViewGroup, toggleLike: (Int) -> Unit) =
            SmallProductViewHolder(
                binding = ViewholderSmallProductBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                toggleLike = toggleLike
            )
    }
}