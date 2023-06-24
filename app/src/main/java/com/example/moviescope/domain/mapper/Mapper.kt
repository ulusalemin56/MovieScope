package com.example.moviescope.domain.mapper

import com.example.moviescope.data.model.movie.Movie
import com.example.moviescope.data.model.series.Series
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.domain.model.SeriesUI

fun List<Movie>.toMovieUI(): List<MovieUI> {
    return this.map {
        MovieUI(
            adult = it.adult,
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

fun List<Series>.toSeriesUI() : List<SeriesUI> {
    return this.map {
        SeriesUI(
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            id = it.id,
            name = it.name,
            overview = it.overview,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage
        )
    }
}