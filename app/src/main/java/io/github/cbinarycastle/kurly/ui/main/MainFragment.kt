package io.github.cbinarycastle.kurly.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.cbinarycastle.kurly.databinding.FragmentMainBinding
import io.github.cbinarycastle.kurly.ui.section.SectionAdapter
import io.github.cbinarycastle.kurly.util.launchAndRepeatWithViewLifecycle

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

        val sectionAdapter = SectionAdapter().also {
            binding.sectionsRecyclerView.adapter = it
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.sections.collect {
                sectionAdapter.submitList(it)
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}