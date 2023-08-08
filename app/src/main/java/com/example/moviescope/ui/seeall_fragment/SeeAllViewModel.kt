package com.example.moviescope.ui.seeall_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviescope.data.repo.MovieScopeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    movieScopeRepository: MovieScopeRepository
) : ViewModel() {
    val seeAllTopRatedMovies =
        movieScopeRepository.getSeeAllTopRatedMovies().cachedIn(viewModelScope)
    val seeAllNowPlayingMovies =
        movieScopeRepository.getSeeAllNowPlayingMovies().cachedIn(viewModelScope)
    val seeAllPopularTvSeries =
        movieScopeRepository.getSeeAllPopularTvSeries().cachedIn(viewModelScope)
    val seeAllTopRatedTvSeries =
        movieScopeRepository.getSeeAllTopRatedTvSeries().cachedIn(viewModelScope)
}