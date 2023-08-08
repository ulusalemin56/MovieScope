package com.example.moviescope.ui.search_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviescope.R
import com.example.moviescope.databinding.FragmentSearchBinding
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.enums.MediaTypeEnum
import com.example.moviescope.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private var mediaType = MediaTypeEnum.DISCOVER_MOVIES
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter(::onClick) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        getSearchData()
        initUI()
        collectData()
        return binding.root
    }

    private fun initUI() {
        with(binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            filterRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.moviesRadioButton -> {
                        mediaType = MediaTypeEnum.DISCOVER_MOVIES
                    }

                    R.id.seriesRadioButton -> {
                        mediaType = MediaTypeEnum.DISCOVER_TV_SERIES
                    }
                }

                val query = searchEditText.text.toString()
                getSearchData(query)
            }

            searchEditText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
                val query = text.toString()
                getSearchData(query)
            })
        }
    }

    private fun getSearchData(query: String = "") {
        if (mediaType == MediaTypeEnum.DISCOVER_MOVIES) {
            if (query.isNotBlank())
                viewModel.getSearchMovies(query)
            else
                viewModel.getDiscoverMovies()
        } else if (mediaType == MediaTypeEnum.DISCOVER_TV_SERIES) {
            if (query.isNotBlank())
                viewModel.getSearchTvSeries(query)
            else
                viewModel.getDiscoverTvSeries()
        }
    }
    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding) {
                    with(viewModel) {
                        searchRV.adapter = searchAdapter
                        discoverData.collectLatest { pagingData ->
                            searchAdapter.submitData(lifecycle, pagingData)
                        }
                    }
                }
            }
        }
    }

    private fun onClick(movieUI: MovieUI) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieUI)
        findNavController().safeNavigate(action)
    }
}