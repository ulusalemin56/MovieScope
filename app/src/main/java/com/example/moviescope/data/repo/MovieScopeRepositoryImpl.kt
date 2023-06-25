package com.example.moviescope.data.repo

import com.example.moviescope.data.source.remote.RemoteDataSource
import com.example.moviescope.domain.mapper.toMovieUI
import com.example.moviescope.domain.mapper.toSeriesUI
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.domain.model.SeriesUI
import com.example.moviescope.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieScopeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieScopeRepository {
    override fun getTopRatedMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getTopRatedMovies().results.toMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
    override fun getNowPlayingMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getNowPlayingMovies().results.toMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getpopularTvSeries(): Flow<Resource<List<SeriesUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getPopularTvSeries().results.toSeriesUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getTopRatedTvSeries(): Flow<Resource<List<SeriesUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getTopRatedTvSeries().results.toSeriesUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
}