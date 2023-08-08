package com.example.moviescope.ui.search_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviescope.data.repo.MovieScopeRepository
import com.example.moviescope.domain.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieScopeRepository: MovieScopeRepository
) : ViewModel() {

    private val _discoverData = MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val discoverData = _discoverData.asStateFlow()

    fun getDiscoverMovies() = viewModelScope.launch {
        movieScopeRepository.getDiscoverMovies().cachedIn(viewModelScope).collectLatest {
            _discoverData.emit(it)
        }
    }

    fun getDiscoverTvSeries() = viewModelScope.launch {
        movieScopeRepository.getDiscoverTvSeries().cachedIn(viewModelScope).collectLatest {
            _discoverData.emit(it)
        }
    }

    fun getSearchMovies(query : String) {

    }

    fun getSearchTvSeries(query : String) {

    }
}