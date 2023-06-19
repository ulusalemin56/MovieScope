package com.example.moviescope.data.repo

import com.example.moviescope.data.model.movie.Movie
import com.example.moviescope.data.model.series.Series
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

    override fun getpopularTvSeries(callback: (List<Series>) -> Unit) {
       remoteDataSource.getPopularTvSeries {
           callback(it.results)
       }
    }

    override fun getTopRatedTvSeries(callback: (List<Series>) -> Unit) {
        remoteDataSource.getTopRatedTvSeries {
            callback(it.results)
        }
    }


}