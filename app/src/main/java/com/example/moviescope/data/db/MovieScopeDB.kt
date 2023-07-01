package com.example.moviescope.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviescope.data.model.local.MovieResponseEntity

@Database(
    entities = [MovieResponseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieScopeDB : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}