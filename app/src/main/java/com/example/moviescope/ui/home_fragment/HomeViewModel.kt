package com.example.moviescope.ui.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.data.model.Movie
import com.example.moviescope.data.repo.MovieScopeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieScopeRepository: MovieScopeRepository
) : ViewModel() {

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> = _topRatedMovies

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> = _nowPlayingMovies

    init {
        getTopRatedMovies()
        getNowPlayingMovies()
    }

    private fun getTopRatedMovies() {
        movieScopeRepository.getTopRatedMovies {
            _topRatedMovies.value = it
        }
    }

    private fun getNowPlayingMovies() {
        movieScopeRepository.getNowPlayingMovies {
            _nowPlayingMovies.value = it
        }
    }
}
