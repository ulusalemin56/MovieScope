package com.example.moviescope.data.repo

import android.accounts.NetworkErrorException
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviescope.data.model.local.BookmarkEntity
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
import kotlinx.coroutines.flow.map
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
                val dataFromRemoteDB =
                    remoteDataSource.getTopRatedMovies().results.movieToMovieUI(MediaTypeEnum.TOP_RATED_MOVIES)
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.TOP_RATED_MOVIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.TOP_RATED_MOVIES)
                if (dataFromLocalDB.isEmpty()) {
                    throw NetworkErrorException("No Internet Connection")
                }
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
                val dataFromRemoteDB =
                    remoteDataSource.getNowPlayingMovies().results.movieToMovieUI(MediaTypeEnum.NOW_PLAYING_MOVIES)
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.NOW_PLAYING_MOVIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.NOW_PLAYING_MOVIES)
                if (dataFromLocalDB.isEmpty()) {
                    throw NetworkErrorException("No Internet Connection")
                }
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
                val dataFromRemoteDB =
                    remoteDataSource.getPopularTvSeries().results.seriesToMovieUI(MediaTypeEnum.POPULAR_TV_SERIES)
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.POPULAR_TV_SERIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.POPULAR_TV_SERIES)
                if (dataFromLocalDB.isEmpty()) {
                    throw NetworkErrorException("No Internet Connection")
                }
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
                val dataFromRemoteDB =
                    remoteDataSource.getTopRatedTvSeries().results.seriesToMovieUI(MediaTypeEnum.TOP_RATED_TV_SERIES)
                emit(Resource.Success(dataFromRemoteDB))
                insertDataToDataBase(dataFromRemoteDB, MediaTypeEnum.TOP_RATED_TV_SERIES)
            } else {
                val dataFromLocalDB = fetchDataFromDataBase(MediaTypeEnum.TOP_RATED_TV_SERIES)
                if (dataFromLocalDB.isEmpty()) {
                    throw NetworkErrorException("No Internet Connection")
                }
                emit(Resource.Success(dataFromLocalDB))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override suspend fun insertMediaToBookmarks(media: BookmarkEntity) {
        localDataSource.insertMediaToBookmarks(media)
    }

    override suspend fun deleteMediaFromBookmarks(media: BookmarkEntity) {
        localDataSource.deleteMediaFromBookmarks(media)
    }

    override fun isBookmarked(id: Int): Flow<Boolean> = flow {
        try {
            emit(localDataSource.isBookmarked(id))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun fetchMediaFromBookmarks(): Flow<Resource<List<BookmarkEntity>>> = flow {
        emit(Resource.Loading)
        try {
            val dataFromBookmarks = localDataSource.fetchMediaFromBookmarks()
            emit(Resource.Success(dataFromBookmarks))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getSeeAllTopRatedMovies(): Flow<PagingData<MovieUI>> = remoteDataSource
        .getSeeAllTopRatedMovies().map {pagingData ->
            pagingData.map {
                it.toMovieUI(MediaTypeEnum.TOP_RATED_MOVIES)
            }
        }


    override fun getSeeAllNowPlayingMovies(): Flow<PagingData<MovieUI>> = remoteDataSource
        .getSeeAllNowPlayingMovies().map { pagingData ->
            pagingData.map {
                it.toMovieUI(MediaTypeEnum.NOW_PLAYING_MOVIES)
            }
        }

    override fun getSeeAllPopularTvSeries(): Flow<PagingData<MovieUI>> = remoteDataSource
        .getSeeAllPopularTvSeries().map {pagingData ->
            pagingData.map {
                it.toMovieUI(MediaTypeEnum.POPULAR_TV_SERIES)
            }
        }

    override fun getSeeAllTopRatedTvSeries(): Flow<PagingData<MovieUI>> = remoteDataSource
        .getSeeAllTopRatedTvSeries().map {pagingData ->
            pagingData.map {
                it.toMovieUI(MediaTypeEnum.TOP_RATED_TV_SERIES)
            }
        }

    private suspend fun insertDataToDataBase(data: List<MovieUI>, mediaTypeEnum: MediaTypeEnum) {
        localDataSource.clearDataMovieResponseWithType(mediaTypeEnum)
        localDataSource.insertMovieResponse(data.toMovieResponseEntityList())
    }

    private suspend fun fetchDataFromDataBase(mediaTypeEnum: MediaTypeEnum): List<MovieUI> {
        return localDataSource.getDataMovieResponseWithType(mediaTypeEnum).toMovieUI()
    }
}