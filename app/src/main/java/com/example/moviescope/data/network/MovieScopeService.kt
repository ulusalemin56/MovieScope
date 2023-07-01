package com.example.moviescope.data.network

import com.example.moviescope.data.model.remote.movie.MoviesResponse
import com.example.moviescope.data.model.remote.series.SeriesResponse
import com.example.moviescope.util.Constants
import retrofit2.http.GET

interface MovieScopeService {
    @GET(Constants.EndPoints.TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(): MoviesResponse
    @GET(Constants.EndPoints.NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(): MoviesResponse
    @GET(Constants.EndPoints.POPULAR_TV_SERIES)
    suspend fun getPopularTvSeries(): SeriesResponse
    @GET(Constants.EndPoints.TOP_RATED_TV_SERIES)
    suspend fun getTopRatedTvSeries(): SeriesResponse
}