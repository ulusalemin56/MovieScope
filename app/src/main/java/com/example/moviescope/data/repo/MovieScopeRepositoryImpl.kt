package com.example.moviescope.data.repo

import com.example.moviescope.data.source.local.LocalDataSource
import com.example.moviescope.data.source.remote.RemoteDataSource
import com.example.moviescope.domain.mapper.seriesToMovieUI
import com.example.moviescope.domain.mapper.movieToMovieUI
import com.example.moviescope.domain.mapper.toMovieResponseEntityList
import com.example.moviescope.domain.mapper.toMovieUI
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Resource
import com.example.moviescope.util.enums.MediaTypeEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieScopeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val isInternetAvailable: Boolean
) : MovieScopeRepository {
    override fun getTopRatedMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            if (isInternetAvailable) {
                val response = remoteDataSource.getTopRatedMovies().results.movieToMovieUI()
                emit(Resource.Success(response))
                localDataSource.clearDataMovieResponseWithType(MediaTypeEnum.TOP_RATED_MOVIES)
                localDataSource.insertMovieResponse(response.toMovieResponseEntityList(MediaTypeEnum.TOP_RATED_MOVIES))
            } else {
                val response =
                    localDataSource.getDataMovieResponseWithType(MediaTypeEnum.TOP_RATED_MOVIES)
                emit(Resource.Success(response.toMovieUI()))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getNowPlayingMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            if (isInternetAvailable) {
                val response = remoteDataSource.getNowPlayingMovies().results.movieToMovieUI()
                emit(Resource.Success(response))
                localDataSource.clearDataMovieResponseWithType(MediaTypeEnum.NOW_PLAYING_MOVIES)
                localDataSource.insertMovieResponse(response.toMovieResponseEntityList(MediaTypeEnum.NOW_PLAYING_MOVIES))
            } else {
                val response =
                    localDataSource.getDataMovieResponseWithType(MediaTypeEnum.NOW_PLAYING_MOVIES)
                emit(Resource.Success(response.toMovieUI()))
            }
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