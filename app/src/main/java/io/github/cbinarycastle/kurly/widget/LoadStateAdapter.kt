package io.github.cbinarycastle.kurly.widget

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.databinding.ViewholderErrorBinding
import io.github.cbinarycastle.kurly.databinding.ViewholderLoadingBinding
import io.github.cbinarycastle.kurly.ui.model.LoadState
import io.github.cbinarycastle.kurly.util.layoutInflater

class LoadStateAdapter(
    private val retry: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var loadState = LoadState.NOT_LOADING
        set(value) {
            if (field != value) {
                val oldItem = displayLoadStateAsItem(field)
                val newItem = displayLoadStateAsItem(value)

                if (oldItem && !newItem) {
                    notifyItemRemoved(0)
                } else if (newItem && !oldItem) {
                    notifyItemInserted(0)
                } else if (oldItem && newItem) {
                    notifyItemChanged(0)
                }
                field = value
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (loadState == LoadState.LOADING) {
            LoadingViewHolder(
                binding = ViewholderLoadingBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                )
            )
        } else {
            ErrorViewHolder(
                binding = ViewholderErrorBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                ),
                retry = retry
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = if (displayLoadStateAsItem(loadState)) 1 else 0

    private fun displayLoadStateAsItem(loadState: LoadState): Boolean =
        loadState == LoadState.LOADING || loadState == LoadState.ERROR
}

class LoadingViewHolder(binding: ViewholderLoadingBinding) : RecyclerView.ViewHolder(binding.root)

class ErrorViewHolder(
    binding: ViewholderErrorBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }
}
