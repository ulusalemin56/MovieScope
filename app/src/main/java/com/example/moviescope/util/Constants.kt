package com.example.moviescope.util

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "68e32a551b801e26fc0aec9f575ba347"
    const val API_PARAM = "api_key"
    const val AUTHORIZATION_PARAM = "Authorization"
    const val AUTHORIZATION_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2OGUzMmE1NTFiODAxZTI2ZmMwYWVjOWY1NzViYTM0NyIsInN1YiI6IjY0OGEyMGQ5YmYzMWYyNTA1NWEzNjRmZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eKSJlSsM_TBnK5tF2gLGgtG1AlEmWP5CpYJnMYxAZRU"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"

    object EndPoints{
        const val TOP_RATED_MOVIES = "movie/top_rated"
        const val NOW_PLAYING_MOVIES = "movie/now_playing"
        const val POPULAR_TV_SERIES = "tv/popular"
        const val TOP_RATED_TV_SERIES = "movie/now_playing"
    }
}