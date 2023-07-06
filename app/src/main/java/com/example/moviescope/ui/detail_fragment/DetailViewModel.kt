package com.example.moviescope.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.repo.MovieScopeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieScopeRepository
): ViewModel() {

    fun insertMediaToBookmarks(media: BookmarkEntity) = viewModelScope.launch {
        repository.insertMediaToBookmarks(media)
    }

    fun deleteMediaFromBookmarks(media: BookmarkEntity) = viewModelScope.launch {
        repository.deleteMediaFromBookmarks(media)
    }

    fun isBookmarked(id: Int) : Boolean {
        var isBookmarked = false
        viewModelScope.launch {
            isBookmarked = repository.isBookmarked(id)
        }
        return isBookmarked
    }
}