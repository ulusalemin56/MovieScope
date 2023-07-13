package com.example.moviescope.ui.home_fragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.data.repo.MovieScopeRepository
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieScopeRepository: MovieScopeRepository
) : ViewModel() {
    // Type casting from Flow type to StateFlow type.
    val topRatedMovies: StateFlow<Resource<List<MovieUI>>> =
        movieScopeRepository.getTopRatedMovies().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading
        )

    // Type casting from Flow type to StateFlow type.
    val nowPlayingMovies: StateFlow<Resource<List<MovieUI>>> =
        movieScopeRepository.getNowPlayingMovies().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading
        )

    // Type casting from Flow type to StateFlow type.
    val popularTvSeries: StateFlow<Resource<List<MovieUI>>> =
        movieScopeRepository.getpopularTvSeries().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading
        )

    // Type casting from Flow type to StateFlow type.
    val topRatedTvSeries: StateFlow<Resource<List<MovieUI>>> =
        movieScopeRepository.getTopRatedTvSeries().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading
        )
}
