package com.example.moviescope.data.source.local

import com.example.moviescope.data.db.MovieDao
import com.example.moviescope.data.model.local.BookmarkEntity
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

    override suspend fun insertMediaToBookmarks(media: BookmarkEntity) {
        movieDao.insertMediaToBookmarks(media)
    }

    override suspend fun deleteMediaFromBookmarks(media: BookmarkEntity) {
        movieDao.deleteMediaFromBookmarks(media)
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return movieDao.isBookmarked(id)
    }

    override suspend fun fetchMediaFromBookmarks(): List<BookmarkEntity> {
        return movieDao.fetchMediaFromBookmarks()
    }
}