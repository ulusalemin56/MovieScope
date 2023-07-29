package com.example.moviescope.data.network

import com.example.moviescope.data.model.remote.movie.MoviesResponse
import com.example.moviescope.data.model.remote.series.SeriesResponse
import com.example.moviescope.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieScopeService {
    @GET(Constants.EndPoints.TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(@Query("page") page : Int = 1): MoviesResponse
    @GET(Constants.EndPoints.NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(@Query("page") page : Int = 1): MoviesResponse
    @GET(Constants.EndPoints.POPULAR_TV_SERIES)
    suspend fun getPopularTvSeries(@Query("page") page : Int = 1): SeriesResponse
    @GET(Constants.EndPoints.TOP_RATED_TV_SERIES)
    suspend fun getTopRatedTvSeries(@Query("page") page : Int = 1): SeriesResponse
}