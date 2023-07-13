package com.example.moviescope.di

import com.example.moviescope.data.network.MovieScopeService
import com.example.moviescope.data.db.MovieDao
import com.example.moviescope.data.db.MovieScopeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ServiceAndDaoModule {
    @Provides
    fun provideMovieScopeService(retrofit: Retrofit): MovieScopeService = retrofit.create(MovieScopeService::class.java)

    @Provides
    fun provideMovieDao(movieScopeDB: MovieScopeDB) : MovieDao = movieScopeDB.movieDao()
}