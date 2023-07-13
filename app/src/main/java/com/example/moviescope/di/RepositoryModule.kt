package com.example.moviescope.di

import com.example.moviescope.data.repo.MovieScopeRepository
import com.example.moviescope.data.repo.MovieScopeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieScopeRepository(movieScopeRepositoryImpl: MovieScopeRepositoryImpl) : MovieScopeRepository

}