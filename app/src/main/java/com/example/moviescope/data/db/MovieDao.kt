package com.example.moviescope.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.util.enums.MediaTypeEnum

@Dao
interface MovieDao {
    @Insert(entity = MovieResponseEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieResponse(movie: List<MovieResponseEntity>)

    @Query("DELETE FROM movies WHERE mediaTypeEnum = :mediaType")
    suspend fun clearDataMovieResponseWithType(mediaType : MediaTypeEnum)

    @Query("SELECT * FROM movies WHERE mediaTypeEnum = :mediaType")
    suspend fun getDataMovieResponseWithType(mediaType : MediaTypeEnum) : List<MovieResponseEntity>
}