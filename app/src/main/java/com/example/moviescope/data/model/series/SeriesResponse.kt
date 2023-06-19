package com.example.moviescope.data.model.series


import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Series>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)