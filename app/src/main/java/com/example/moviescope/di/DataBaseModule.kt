package com.example.moviescope.di

import android.content.Context
import androidx.room.Room
import com.example.moviescope.data.db.MovieScopeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideMovieScopeDB(@ApplicationContext context : Context) : MovieScopeDB {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieScopeDB::class.java,
            "moviescope_data_base"
        ).build()
    }
}