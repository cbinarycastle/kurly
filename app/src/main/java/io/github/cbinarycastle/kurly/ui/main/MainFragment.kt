package io.github.cbinarycastle.kurly.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import io.github.cbinarycastle.kurly.databinding.FragmentMainBinding
import io.github.cbinarycastle.kurly.ui.section.SectionAdapter
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

        val sectionAdapter = SectionAdapter()
        val loadStateAdapter = LoadStateAdapter(retry = {})
        binding.sectionsRecyclerView.adapter = ConcatAdapter(sectionAdapter, loadStateAdapter)

        binding.sectionsRecyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val canScrollDown = recyclerView.canScrollVertically(1)

                val lastVisibleItemPosition = (recyclerView.layoutManager as? LinearLayoutManager)
                    ?.findLastVisibleItemPosition() ?: NO_POSITION
                val isScrollBottom =
                    lastVisibleItemPosition == (recyclerView.adapter?.itemCount ?: 0) - 1

                if (isScrollBottom && !canScrollDown) {
                    viewModel.loadMore()
                }
            }
        })

        launchAndRepeatWithViewLifecycle {
            viewModel.sections.collect {
                sectionAdapter.submitList(it.data) {
                    loadStateAdapter.loadState = it.loadState
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}