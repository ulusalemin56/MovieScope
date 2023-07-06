package com.example.moviescope.domain.model

import android.os.Parcelable
import com.example.moviescope.util.enums.MediaTypeEnum
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieUI(
    val backdropPath: String?,
    val id: Int,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val mediaTypeEnum: MediaTypeEnum
) : Parcelable
