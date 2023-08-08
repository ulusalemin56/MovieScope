package com.example.moviescope.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.model.remote.movie.MoviesResponse
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.data.model.remote.series.SeriesResponse
import com.example.moviescope.data.network.MovieScopeService
import com.example.moviescope.data.paging_source.MoviePagingSource
import com.example.moviescope.data.paging_source.SeriesPagingSource
import com.example.moviescope.util.Constants.NETWORK_PAGE_SIZE
import com.example.moviescope.util.enums.MovieTypeEnum
import com.example.moviescope.util.enums.SerieTypeEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieScopeService: MovieScopeService
) : RemoteDataSource {
    override suspend fun getTopRatedMovies(): MoviesResponse = movieScopeService.getTopRatedMovies()
    override suspend fun getNowPlayingMovies(): MoviesResponse =
        movieScopeService.getNowPlayingMovies()

    override suspend fun getPopularTvSeries(): SeriesResponse =
        movieScopeService.getPopularTvSeries()

    override suspend fun getTopRatedTvSeries(): SeriesResponse =
        movieScopeService.getTopRatedTvSeries()

    override fun getSeeAllTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieScopeService, MovieTypeEnum.TOP_RATED_MOVIES)
            }
        ).flow
    }

    override fun getSeeAllNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieScopeService, MovieTypeEnum.NOW_PLAYING_MOVIES)
            }
        ).flow
    }

    override fun getSeeAllPopularTvSeries(): Flow<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                SeriesPagingSource(movieScopeService, SerieTypeEnum.POPULAR_TV_SERIES)
            }
        ).flow
    }

    override fun getSeeAllTopRatedTvSeries(): Flow<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                SeriesPagingSource(movieScopeService, SerieTypeEnum.TOP_RATED_TV_SERIES)
            }
        ).flow
    }

    override fun getDiscoverMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieScopeService, MovieTypeEnum.DISCOVER_MOVIES)
            }
        ).flow
    }

    override fun getDiscoverTvSeries(): Flow<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                SeriesPagingSource(movieScopeService, SerieTypeEnum.DISCOVER_TV_SERIES)
            }
        ).flow
    }

    override fun getSearchMovie(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieScopeService, MovieTypeEnum.SEARCH_MOVIE, query)
            }
        ).flow
    }
    override fun getSearchTvSerie(query: String): Flow<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                SeriesPagingSource(movieScopeService, SerieTypeEnum.SEARCH_SERIE,query)
            }
        ).flow
    }
}