package com.example.moviescope.data.repo

import androidx.paging.PagingData
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieScopeRepository {
    fun getTopRatedMovies(): Flow<Resource<List<MovieUI>>>
    fun getNowPlayingMovies(): Flow<Resource<List<MovieUI>>>
    fun getpopularTvSeries(): Flow<Resource<List<MovieUI>>>
    fun getTopRatedTvSeries(): Flow<Resource<List<MovieUI>>>
    suspend fun insertMediaToBookmarks(media: BookmarkEntity)
    suspend fun deleteMediaFromBookmarks(media: BookmarkEntity)
    fun isBookmarked(id: Int): Flow<Boolean>
    fun fetchMediaFromBookmarks(): Flow<Resource<List<BookmarkEntity>>>
    fun getSeeAllTopRatedMovies(): Flow<PagingData<MovieUI>>
    fun getSeeAllNowPlayingMovies(): Flow<PagingData<MovieUI>>
    fun getSeeAllPopularTvSeries() : Flow<PagingData<MovieUI>>
    fun getSeeAllTopRatedTvSeries() : Flow<PagingData<MovieUI>>
    fun getDiscoverMovies() : Flow<PagingData<MovieUI>>
    fun getDiscoverTvSeries() : Flow<PagingData<MovieUI>>
}