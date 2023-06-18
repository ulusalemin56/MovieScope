package com.example.moviescope.ui.home_fragment


import com.example.moviescope.data.repo.MovieScopeRepository
import com.example.moviescope.data.repo.MovieScopeRepositoryImpl
import com.example.moviescope.data.source.remote.RemoteDataSource
import com.example.moviescope.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun provideMovieScopeRepository(movieScopeRepositoryImpl: MovieScopeRepositoryImpl) : MovieScopeRepository
}