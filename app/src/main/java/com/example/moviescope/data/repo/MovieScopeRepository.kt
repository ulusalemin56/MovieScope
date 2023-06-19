package com.example.moviescope.data.repo

import com.example.moviescope.data.model.movie.Movie
import com.example.moviescope.data.model.series.Series

interface MovieScopeRepository {
    fun getTopRatedMovies(callback : (List<Movie>) -> Unit)

    fun getNowPlayingMovies(callback : (List<Movie>) -> Unit)

    fun getpopularTvSeries(callback : (List<Series>) -> Unit)

    fun getTopRatedTvSeries(callback : (List<Series>) -> Unit)
}