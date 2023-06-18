package com.example.moviescope.data.repo

import com.example.moviescope.data.model.Movie

interface MovieScopeRepository {
    fun getTopRatedMovies(callback : (List<Movie>) -> Unit)

    fun getNowPlayingMovies(callback : (List<Movie>) -> Unit)
}