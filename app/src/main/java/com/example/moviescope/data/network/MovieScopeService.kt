package com.example.moviescope.data.network

import com.example.moviescope.data.model.movie.MoviesResponse
import com.example.moviescope.data.model.series.SeriesResponse
import com.example.moviescope.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface MovieScopeService {
    @GET(Constants.EndPoints.TOP_RATED_MOVIES)
    fun getTopRatedMovies(): Call<MoviesResponse>

    @GET(Constants.EndPoints.NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(): Call<MoviesResponse>

    @GET(Constants.EndPoints.POPULAR_TV_SERIES)
    fun getPopularTvSeries(): Call<SeriesResponse>

    @GET(Constants.EndPoints.TOP_RATED_TV_SERIES)
    fun getTopRatedTvSeries(): Call<SeriesResponse>


}