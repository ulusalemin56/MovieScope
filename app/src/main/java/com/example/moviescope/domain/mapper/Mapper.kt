package com.example.moviescope.domain.mapper

import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.enums.MediaTypeEnum

fun List<Movie>.movieToMovieUI(): List<MovieUI> {
    return this.map {
        MovieUI(
            backdropPath = it.backdropPath,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
        )
    }
}

fun List<Series>.seriesToMovieUI() : List<MovieUI> {
    return this.map {
        MovieUI(
            backdropPath = it.backdropPath,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.firstAirDate,
            title = it.name,
            voteAverage = it.voteAverage,
        )
    }
}

fun List<MovieUI>.toMovieResponseEntityList(mediaTypeEnum: MediaTypeEnum) : List<MovieResponseEntity> {
    return this.map {
        MovieResponseEntity(
            id = it.id,
            backdropPath = it.backdropPath,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            mediaTypeEnum = mediaTypeEnum
        )
    }
}

fun List<MovieResponseEntity>.toMovieUI() : List<MovieUI> {
    return this.map {
        MovieUI(
            id = it.id,
            backdropPath = it.backdropPath,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage
        )
    }
}