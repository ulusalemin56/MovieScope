package com.example.moviescope.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviescope.data.model.remote.series.Series
import com.example.moviescope.data.network.MovieScopeService
import com.example.moviescope.util.Constants.STARTING_PAGE_INDEX
import com.example.moviescope.util.enums.SerieTypeEnum

class SeriesPagingSource(
    private val movieScopeService: MovieScopeService,
    private val mediaType: SerieTypeEnum,
    private val query: String = ""
) : PagingSource<Int, Series>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX

            val response = when (mediaType) {
                SerieTypeEnum.POPULAR_TV_SERIES -> {
                    movieScopeService.getPopularTvSeries(page).results
                }

                SerieTypeEnum.TOP_RATED_TV_SERIES -> {
                    movieScopeService.getTopRatedTvSeries(page).results
                }

                SerieTypeEnum.DISCOVER_TV_SERIES -> {
                    movieScopeService.getDiscoverTvSeries(page).results
                }

                SerieTypeEnum.SEARCH_SERIE -> {
                    movieScopeService.getSearchTvSerie(query, page).results
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

    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}