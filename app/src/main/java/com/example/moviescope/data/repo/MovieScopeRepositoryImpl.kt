package com.example.moviescope.data.repo

import com.example.moviescope.data.model.Movie
import com.example.moviescope.data.source.remote.RemoteDataSource
import javax.inject.Inject

class MovieScopeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieScopeRepository {

    override fun getTopRatedMovies(callback : (List<Movie>) -> Unit) {
        remoteDataSource.getTopRatedMovies(){
            callback(it.results)
        }
    }

    override fun getNowPlayingMovies(callback: (List<Movie>) -> Unit) {
        remoteDataSource.getNowPlayingMovies {
            callback(it.results)
        }
    }


}