package com.example.moviescope.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviescope.util.enums.MediaTypeEnum
import java.util.Calendar

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: Int,
    val backdropPath: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val mediaTypeEnum : MediaTypeEnum,
    val addDate: String = Calendar.getInstance().time.toString()
)
