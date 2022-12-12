package io.github.cbinarycastle.kurly.ui.product

import androidx.recyclerview.widget.DiffUtil
import io.github.cbinarycastle.kurly.domain.model.Product

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}