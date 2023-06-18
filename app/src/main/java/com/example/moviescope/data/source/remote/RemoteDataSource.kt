package com.example.moviescope.data.source.remote


import com.example.moviescope.data.model.MoviesResponse

interface RemoteDataSource {
    fun getTopRatedMovies(callback : (MoviesResponse) -> Unit)

    fun getNowPlayingMovies(callback : (MoviesResponse) -> Unit)
}