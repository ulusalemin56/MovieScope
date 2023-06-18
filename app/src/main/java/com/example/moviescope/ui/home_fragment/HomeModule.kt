package com.example.moviescope.ui.home_fragment

import com.example.moviescope.data.network.MovieScopeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideMovieScopeService(retrofit: Retrofit) : MovieScopeService {
        return retrofit.create(MovieScopeService::class.java)
    }

}