package com.example.moviescope.data.source.remote

import com.example.moviescope.data.model.MoviesResponse
import com.example.moviescope.data.network.MovieScopeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieScopeService: MovieScopeService
) : RemoteDataSource {

    override fun getTopRatedMovies(callback : (MoviesResponse) -> Unit) {
        movieScopeService.getTopRatedMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        callback(it)
                    }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun getNowPlayingMovies(callback: (MoviesResponse) -> Unit) {
        movieScopeService.getNowPlayingMovies().enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
               if (response.isSuccessful)
                   response.body()?.let{
                       callback(it)
                   }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}