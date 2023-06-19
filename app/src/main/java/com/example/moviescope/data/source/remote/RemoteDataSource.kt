package com.example.moviescope.data.source.remote


import com.example.moviescope.data.model.movie.MoviesResponse
import com.example.moviescope.data.model.series.SeriesResponse

interface RemoteDataSource {
    fun getTopRatedMovies(callback : (MoviesResponse) -> Unit)

    fun getNowPlayingMovies(callback : (MoviesResponse) -> Unit)

    fun getPopularTvSeries(callback : (SeriesResponse) -> Unit)

    fun getTopRatedTvSeries(callback : (SeriesResponse) -> Unit)
}