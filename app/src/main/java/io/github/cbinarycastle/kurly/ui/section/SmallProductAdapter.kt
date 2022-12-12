package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.databinding.ViewholderSmallProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class SmallProductAdapter(
    private val toggleLike: (Product) -> Unit,
) : ListAdapter<Product, SmallProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallProductViewHolder {
        return SmallProductViewHolder(
            binding = ViewholderSmallProductBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            ),
            toggleLike = { position -> toggleLike(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: SmallProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}