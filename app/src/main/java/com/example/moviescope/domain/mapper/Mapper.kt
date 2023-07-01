package com.example.moviescope.domain.mapper

import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.domain.model.MovieUI

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