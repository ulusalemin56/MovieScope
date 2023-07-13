package com.example.moviescope.data.source.remote

import com.example.moviescope.data.model.remote.movie.MoviesResponse
import com.example.moviescope.data.model.remote.series.SeriesResponse
import com.example.moviescope.data.network.MovieScopeService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieScopeService: MovieScopeService
) : RemoteDataSource {
    override suspend fun getTopRatedMovies(): MoviesResponse = movieScopeService.getTopRatedMovies()
    override suspend fun getNowPlayingMovies(): MoviesResponse = movieScopeService.getNowPlayingMovies()
    override suspend fun getPopularTvSeries(): SeriesResponse = movieScopeService.getPopularTvSeries()
    override suspend fun getTopRatedTvSeries(): SeriesResponse = movieScopeService.getTopRatedTvSeries()
}