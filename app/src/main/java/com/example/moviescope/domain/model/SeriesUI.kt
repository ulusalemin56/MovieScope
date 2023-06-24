package com.example.moviescope.domain.model

data class SeriesUI(
    val backdropPath: String?,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double,
    var isFavorite: Boolean = false
)
