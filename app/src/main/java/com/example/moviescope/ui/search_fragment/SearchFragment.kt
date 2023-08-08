package com.example.moviescope.ui.search_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviescope.R
import com.example.moviescope.databinding.FragmentSearchBinding
import com.example.moviescope.util.enums.MediaTypeEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private var mediaType = MediaTypeEnum.DISCOVER_MOVIES
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        getFilterInfo()
        getDiscoverData()
        initUI()
        collectData()
        return binding.root
    }

    private fun initUI() {
        with(binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            searchEditText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
                val query = text.toString()
                if (query.isNotBlank()) {
                    getSearchData(query)
                } else {
                    getDiscoverData()
                }
            })
        }
    }

    private fun getSearchData(query : String) {
        if (mediaType == MediaTypeEnum.DISCOVER_MOVIES) {
            viewModel.getSearchMovies(query)
            Toast.makeText(requireContext(),    "Search Movie: $query", Toast.LENGTH_LONG).show()
        } else if (mediaType == MediaTypeEnum.DISCOVER_TV_SERIES) {
            viewModel.getSearchTvSeries(query)
            Toast.makeText(requireContext(),    "Search Series: $query", Toast.LENGTH_LONG).show()
        }
    }
    private fun getDiscoverData() {
        if (mediaType == MediaTypeEnum.DISCOVER_MOVIES) {
            viewModel.getDiscoverMovies()
        } else if (mediaType == MediaTypeEnum.DISCOVER_TV_SERIES) {
            viewModel.getDiscoverTvSeries()
        }
    }

    private fun getFilterInfo() {
        binding.filterRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.moviesRadioButton -> {
                    mediaType = MediaTypeEnum.DISCOVER_MOVIES
                    getDiscoverData()
                }

                R.id.seriesRadioButton -> {
                    mediaType = MediaTypeEnum.DISCOVER_TV_SERIES
                    getDiscoverData()
                }
            }
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

}