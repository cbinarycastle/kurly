package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.domain.model.Product

class LargeProductAdapter(
    private val toggleLikeProduct: (Product) -> Unit,
) : ListAdapter<Product, LargeProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeProductViewHolder {
        return LargeProductViewHolder.create(
            parent = parent,
            toggleLikeProduct = { position -> toggleLikeProduct(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: LargeProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}