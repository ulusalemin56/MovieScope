package com.example.moviescope.ui.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.data.model.MoviesResponse
import com.example.moviescope.data.network.MovieScopeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class HomeViewModel : ViewModel() {

    private val _movieData = MutableLiveData<MoviesResponse>()
    val movie : LiveData<MoviesResponse> = _movieData

    fun fetchData() {
        val moviewScopeService = MovieScopeService.getMovieScopeService()
        moviewScopeService.getTopRatedMovies().enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {

                if (response.isSuccessful) {
                   response.body()?.let {
                        _movieData.value = it
                    }
                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
