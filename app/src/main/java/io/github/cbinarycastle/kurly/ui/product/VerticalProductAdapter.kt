package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product

class VerticalProductAdapter(
    private val toggleLikeProduct: (Product) -> Unit,
) : ListAdapter<Product, VerticalProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalProductViewHolder {
        return VerticalProductViewHolder.create(
            parent = parent,
            toggleLikeProduct = { position -> toggleLikeProduct(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: VerticalProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}