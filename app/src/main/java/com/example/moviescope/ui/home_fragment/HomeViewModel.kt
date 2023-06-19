package com.example.moviescope.ui.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.data.model.movie.Movie
import com.example.moviescope.data.model.series.Series
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

    private val _popularTvSeries = MutableLiveData<List<Series>>()
    val popularTvSeries: LiveData<List<Series>> = _popularTvSeries

    private val _topRatedTvSeries = MutableLiveData<List<Series>>()
    val topRatedTvSeries: LiveData<List<Series>> = _topRatedTvSeries

    init {
        getTopRatedMovies()
        getNowPlayingMovies()
        getPopularTvSeries()
        getTopRatedTvSeries()
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

    private fun getPopularTvSeries() {
        movieScopeRepository.getpopularTvSeries {
            _popularTvSeries.value = it
        }
    }

    private fun getTopRatedTvSeries() {
        movieScopeRepository.getTopRatedTvSeries {
            _topRatedTvSeries.value = it
        }
    }
}
