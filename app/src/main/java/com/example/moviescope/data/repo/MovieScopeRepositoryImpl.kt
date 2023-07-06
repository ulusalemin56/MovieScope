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
                val dataFromRemoteDB = remoteDataSource.getTopRatedMovies().results.movieToMovieUI()
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.TOP_RATED_MOVIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.TOP_RATED_MOVIES)
                emit(Resource.Success(dataFromLocalDB))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getNowPlayingMovies(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            if (isInternetAvailable) {
                val dataFromRemoteDB = remoteDataSource.getNowPlayingMovies().results.movieToMovieUI()
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.NOW_PLAYING_MOVIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.NOW_PLAYING_MOVIES)
                emit(Resource.Success(dataFromLocalDB))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getpopularTvSeries(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            if (isInternetAvailable) {
                val dataFromRemoteDB = remoteDataSource.getPopularTvSeries().results.seriesToMovieUI()
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.POPULAR_TV_SERIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.POPULAR_TV_SERIES)
                emit(Resource.Success(dataFromLocalDB))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getTopRatedTvSeries(): Flow<Resource<List<MovieUI>>> = flow {
        emit(Resource.Loading)
        try {
            if (isInternetAvailable) {
                val dataFromRemoteDB = remoteDataSource.getTopRatedTvSeries().results.seriesToMovieUI()
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.TOP_RATED_TV_SERIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.TOP_RATED_TV_SERIES)
                emit(Resource.Success(dataFromLocalDB))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    private suspend fun insertDataToDataBase(data : List<MovieUI>, mediaTypeEnum: MediaTypeEnum) {
        localDataSource.clearDataMovieResponseWithType(mediaTypeEnum)
        localDataSource.insertMovieResponse(data.toMovieResponseEntityList(mediaTypeEnum))
    }
    private suspend fun fetchDataFromDataBase(mediaTypeEnum: MediaTypeEnum) : List<MovieUI> {
        return localDataSource.getDataMovieResponseWithType(mediaTypeEnum).toMovieUI()
    }
}