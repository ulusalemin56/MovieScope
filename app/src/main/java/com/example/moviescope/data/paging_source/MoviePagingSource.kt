package com.example.moviescope.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviescope.data.model.remote.movie.Movie
import com.example.moviescope.data.network.MovieScopeService
import com.example.moviescope.util.Constants.STARTING_PAGE_INDEX
import com.example.moviescope.util.enums.MovieTypeEnum

class MoviePagingSource(
    private val movieScopeService: MovieScopeService,
    private val mediaType: MovieTypeEnum,
    private val query: String = ""
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX

            val response = when (mediaType) {
                MovieTypeEnum.TOP_RATED_MOVIES -> {
                    movieScopeService.getTopRatedMovies(page).results
                }

                MovieTypeEnum.NOW_PLAYING_MOVIES -> {
                    movieScopeService.getNowPlayingMovies(page).results
                }

                MovieTypeEnum.DISCOVER_MOVIES -> {
                    movieScopeService.getDiscoverMovies(page).results
                }

                MovieTypeEnum.SEARCH_MOVIE -> {
                    movieScopeService.getSearchMovie(query, page).results
                }
            }

            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}