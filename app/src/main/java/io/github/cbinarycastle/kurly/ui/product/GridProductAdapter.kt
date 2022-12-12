package io.github.cbinarycastle.kurly.ui.product

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product

class GridProductAdapter(
    private val toggleLike: (Product) -> Unit,
) : ListAdapter<Product, GridProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridProductViewHolder {
        return GridProductViewHolder.create(
            parent = parent,
            toggleLike = { position -> toggleLike(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: GridProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}