package com.example.moviescope.domain.model

data class MovieUI(
    val adult : Boolean,
    val backdropPath: String?,
    val id: Int,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    var isFavorite : Boolean = false
)
