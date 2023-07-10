package com.example.moviescope.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.repo.MovieScopeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieScopeRepository
) : ViewModel() {

    private val _isBookmarked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked

    fun insertMediaToBookmarks(media: BookmarkEntity) = viewModelScope.launch {
        repository.insertMediaToBookmarks(media)
    }

    fun deleteMediaFromBookmarks(media: BookmarkEntity) = viewModelScope.launch {
        repository.deleteMediaFromBookmarks(media)
    }

    fun initIsBookmarked(id: Int) = viewModelScope.launch {
        repository.isBookmarked(id).collectLatest {
            _isBookmarked.emit(it)
        }
    }
}