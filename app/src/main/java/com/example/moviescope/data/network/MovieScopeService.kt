package com.example.moviescope.data.network

import com.example.moviescope.data.model.MoviesResponse
import com.example.moviescope.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieScopeService {

    @GET(Constants.EndPoints.TOP_RATED_MOVIES)
    fun getTopRatedMovies() : Call<MoviesResponse>

    companion object {
        @Volatile
        private var movieScopeService : MovieScopeService? = null

        fun getMovieScopeService() : MovieScopeService {
            val httpLoginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor {chain ->
                    val request = chain.request()

                   val myRequest = request.newBuilder()
                        .addHeader(Constants.AUTHORIZATION_PARAM, Constants.AUTHORIZATION_KEY)
                        .build()

                    chain.proceed(myRequest)
                }
                .addNetworkInterceptor(httpLoginInterceptor)
                .build()

            return movieScopeService ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                val api = retrofit.create(MovieScopeService::class.java)
                 movieScopeService = api
                api
            }
        }
    }
}