package com.example.moviescope.di

import com.example.moviescope.data.source.remote.RemoteDataSource
import com.example.moviescope.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}