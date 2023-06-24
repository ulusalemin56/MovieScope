package com.example.moviescope.data.source.remote


import com.example.moviescope.data.model.movie.MoviesResponse
import com.example.moviescope.data.model.series.SeriesResponse

interface RemoteDataSource {
    suspend fun getTopRatedMovies() : MoviesResponse

    suspend fun getNowPlayingMovies() : MoviesResponse

    suspend fun getPopularTvSeries() : SeriesResponse

    suspend fun getTopRatedTvSeries() : SeriesResponse
}