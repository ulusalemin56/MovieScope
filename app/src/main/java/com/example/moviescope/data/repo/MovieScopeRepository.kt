package com.example.moviescope.data.repo

import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieScopeRepository {
    fun getTopRatedMovies() : Flow<Resource<List<MovieUI>>>
    fun getNowPlayingMovies() : Flow<Resource<List<MovieUI>>>
    fun getpopularTvSeries()  : Flow<Resource<List<MovieUI>>>
    fun getTopRatedTvSeries() : Flow<Resource<List<MovieUI>>>
}