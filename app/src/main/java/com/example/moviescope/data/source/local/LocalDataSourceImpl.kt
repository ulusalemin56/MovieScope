package com.example.moviescope.data.source.local

import com.example.moviescope.data.db.MovieDao
import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.util.enums.MediaTypeEnum
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao : MovieDao
) : LocalDataSource {
    override suspend fun insertMovieResponse(movies: List<MovieResponseEntity>) {
        movieDao.insertMovieResponse(movies)
    }

    override suspend fun clearDataMovieResponseWithType(mediaType: MediaTypeEnum) {
        movieDao.clearDataMovieResponseWithType(mediaType)
    }

    override suspend fun getDataMovieResponseWithType(mediaType: MediaTypeEnum): List<MovieResponseEntity> {
        return movieDao.getDataMovieResponseWithType(mediaType)
    }
}