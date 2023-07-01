package com.example.moviescope.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviescope.util.enums.MediaTypeEnum

@Entity(tableName = "movies")
data class MovieResponseEntity(
    @PrimaryKey
    val id: Int,
    val backdropPath: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val mediaTypeEnum : MediaTypeEnum
)
