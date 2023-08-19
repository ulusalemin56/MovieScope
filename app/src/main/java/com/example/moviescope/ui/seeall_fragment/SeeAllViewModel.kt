package com.example.moviescope.ui.seeall_fragment

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
class SeeAllViewModel @Inject constructor(
    private val movieScopeRepository: MovieScopeRepository
) : ViewModel() {
    private val _fetchData = MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val fetchData = _fetchData.asStateFlow()
    fun seeAllTopRatedMovies() = viewModelScope.launch {
        movieScopeRepository.getSeeAllTopRatedMovies().cachedIn(viewModelScope).collectLatest {
            _fetchData.emit(it)
        }
    }
    fun seeAllNowPlayingMovies() = viewModelScope.launch {
        movieScopeRepository.getSeeAllNowPlayingMovies().cachedIn(viewModelScope).collectLatest {
            _fetchData.emit(it)
        }
    }
    fun seeAllPopularTvSeries() = viewModelScope.launch {
        movieScopeRepository.getSeeAllPopularTvSeries().cachedIn(viewModelScope).collectLatest {
            _fetchData.emit(it)
        }
    }
    fun seeAllTopRatedTvSeries() = viewModelScope.launch {
        movieScopeRepository.getSeeAllTopRatedTvSeries().cachedIn(viewModelScope).collectLatest {
            _fetchData.emit(it)
        }
    }
}