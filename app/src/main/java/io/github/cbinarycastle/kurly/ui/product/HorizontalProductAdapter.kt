package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product

class HorizontalProductAdapter(
    private val toggleLike: (Product) -> Unit,
) : ListAdapter<Product, HorizontalProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalProductViewHolder {
        return HorizontalProductViewHolder.create(
            parent = parent,
            toggleLike = { position -> toggleLike(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: HorizontalProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}