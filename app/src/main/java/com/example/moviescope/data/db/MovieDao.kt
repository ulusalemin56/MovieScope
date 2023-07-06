package com.example.moviescope.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.util.enums.MediaTypeEnum
@Dao
interface MovieDao {
    @Insert(entity = MovieResponseEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieResponse(movie: List<MovieResponseEntity>)

    @Query("DELETE FROM movies WHERE mediaTypeEnum = :mediaType")
    suspend fun clearDataMovieResponseWithType(mediaType: MediaTypeEnum)

    @Query("SELECT * FROM movies WHERE mediaTypeEnum = :mediaType ORDER BY voteAverage,id DESC")
    suspend fun getDataMovieResponseWithType(mediaType: MediaTypeEnum): List<MovieResponseEntity>

    @Insert(entity = BookmarkEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaToBookmarks(media: BookmarkEntity)

    @Delete
    suspend fun deleteMediaFromBookmarks(media: BookmarkEntity)

    @Query("SELECT EXISTS(SELECT * FROM bookmarks WHERE id = :id)")
    suspend fun isBookmarked(id: Int) : Boolean

    @Query("SELECT * FROM bookmarks ORDER BY addDate DESC")
    suspend fun fetchMediaFromBookmarks(): List<BookmarkEntity>
}