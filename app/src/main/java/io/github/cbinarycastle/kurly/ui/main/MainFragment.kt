package io.github.cbinarycastle.kurly.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.github.cbinarycastle.kurly.databinding.FragmentMainBinding
import io.github.cbinarycastle.kurly.ui.model.LoadState
import io.github.cbinarycastle.kurly.ui.section.SectionAdapter
import io.github.cbinarycastle.kurly.util.infiniteScroll
import io.github.cbinarycastle.kurly.util.launchAndRepeatWithViewLifecycle
import io.github.cbinarycastle.kurly.widget.LoadStateAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        val sectionAdapter = SectionAdapter(viewModel::toggleLikeProduct)
        val loadStateAdapter = LoadStateAdapter(viewModel::retry)

        with(binding.sectionsRecyclerView) {
            adapter = ConcatAdapter(sectionAdapter, loadStateAdapter)
            infiniteScroll { viewModel.loadMore() }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.sections.collect {
                sectionAdapter.submitList(it.data) {
                    loadStateAdapter.loadState = it.loadStates.append
                }
                binding.swipeRefreshLayout.isRefreshing = it.loadStates.refresh == LoadState.LOADING
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}