package com.example.moviescope.domain.mapper

import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.model.local.MovieResponseEntity
import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.enums.MediaTypeEnum

fun List<Movie>.movieToMovieUI(mediaTypeEnum: MediaTypeEnum): List<MovieUI> {
    return this.map {
        MovieUI(
            backdropPath = it.backdropPath,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            mediaTypeEnum = mediaTypeEnum
        )
    }
}

fun List<Series>.seriesToMovieUI(mediaTypeEnum: MediaTypeEnum) : List<MovieUI> {
    return this.map {
        MovieUI(
            backdropPath = it.backdropPath,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.firstAirDate,
            title = it.name,
            voteAverage = it.voteAverage,
            mediaTypeEnum = mediaTypeEnum
        )
    }
}

fun List<MovieUI>.toMovieResponseEntityList() : List<MovieResponseEntity> {
    return this.map {
        MovieResponseEntity(
            id = it.id,
            backdropPath = it.backdropPath,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            mediaTypeEnum = it.mediaTypeEnum
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
            voteAverage = it.voteAverage,
            mediaTypeEnum = it.mediaTypeEnum
        )
    }
}

fun MovieUI.toBookmarkEntity() : BookmarkEntity {
    return BookmarkEntity(
        id = id,
        backdropPath = backdropPath,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        mediaTypeEnum = mediaTypeEnum
    )
}

fun BookmarkEntity.toMovieUI() : MovieUI {
    return MovieUI(
        id = id,
        backdropPath = backdropPath,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        mediaTypeEnum = mediaTypeEnum
    )
}