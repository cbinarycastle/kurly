package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product

class SmallProductAdapter(
    private val toggleLike: (Product) -> Unit,
) : ListAdapter<Product, SmallProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallProductViewHolder {
        return SmallProductViewHolder.create(
            parent = parent,
            toggleLike = { position -> toggleLike(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: SmallProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}