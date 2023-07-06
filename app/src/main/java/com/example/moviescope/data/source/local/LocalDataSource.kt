package com.example.moviescope.data.source.local

import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.util.enums.MediaTypeEnum

interface LocalDataSource {
    suspend fun insertMovieResponse(movies: List<MovieResponseEntity>)
    suspend fun clearDataMovieResponseWithType(mediaType : MediaTypeEnum)
    suspend fun getDataMovieResponseWithType(mediaType : MediaTypeEnum) : List<MovieResponseEntity>
    suspend fun insertMediaToBookmarks(media: BookmarkEntity)
    suspend fun deleteMediaFromBookmarks(media: BookmarkEntity)
    suspend fun isBookmarked(id: Int) : Boolean
    suspend fun fetchMediaFromBookmarks(): List<BookmarkEntity>
}