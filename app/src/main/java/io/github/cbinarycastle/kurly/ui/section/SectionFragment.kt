package io.github.cbinarycastle.kurly.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.cbinarycastle.kurly.databinding.FragmentSectionBinding

class SectionFragment : Fragment() {

    private val viewModel: SectionViewModel by viewModels()
    private lateinit var binding: FragmentSectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val section = requireNotNull(
            requireArguments().getParcelable<SectionModel>(ARGS_SECTION)
        )
        viewModel.setSection(section)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val ARGS_SECTION = "section"

        fun newInstance(section: SectionModel): SectionFragment {
            return SectionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGS_SECTION, section)
                }
            }
        }
    }
}