package io.github.cbinarycastle.kurly.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.cbinarycastle.kurly.databinding.ViewholderLargeProductBinding
import io.github.cbinarycastle.kurly.domain.model.Product
import io.github.cbinarycastle.kurly.util.layoutInflater

class LargeProductAdapter : ListAdapter<Product, LargeProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeProductViewHolder {
        return LargeProductViewHolder(
            binding = ViewholderLargeProductBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LargeProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}