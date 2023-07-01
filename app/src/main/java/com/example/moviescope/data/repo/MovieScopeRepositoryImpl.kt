package com.example.moviescope.data.repo

import com.example.moviescope.data.source.remote.RemoteDataSource
import com.example.moviescope.domain.mapper.seriesToMovieUI
import com.example.moviescope.domain.mapper.movieToMovieUI
import com.example.moviescope.domain.model.MovieUI
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
            val response = remoteDataSource.getTopRatedMovies().results.movieToMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
    override fun getNowPlayingMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getNowPlayingMovies().results.movieToMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getpopularTvSeries(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getPopularTvSeries().results.seriesToMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getTopRatedTvSeries(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getTopRatedTvSeries().results.seriesToMovieUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
}