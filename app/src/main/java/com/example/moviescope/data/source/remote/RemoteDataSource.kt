package com.example.moviescope.data.source.remote


import androidx.paging.PagingData
import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.model.remote.movie.MoviesResponse
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.data.model.remote.series.SeriesResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getTopRatedMovies(): MoviesResponse
    suspend fun getNowPlayingMovies(): MoviesResponse
    suspend fun getPopularTvSeries(): SeriesResponse
    suspend fun getTopRatedTvSeries(): SeriesResponse
    fun getSeeAllTopRatedMovies() : Flow<PagingData<Movie>>
    fun getSeeAllNowPlayingMovies() : Flow<PagingData<Movie>>
    fun getSeeAllPopularTvSeries() : Flow<PagingData<Series>>
    fun getSeeAllTopRatedTvSeries() : Flow<PagingData<Series>>
    fun getDiscoverMovies() : Flow<PagingData<Movie>>
    fun getDiscoverTvSeries() : Flow<PagingData<Series>>
    fun getSearchMovie(query : String) : Flow<PagingData<Movie>>
    fun getSearchTvSerie(query : String) : Flow<PagingData<Series>>
}