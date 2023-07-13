package com.example.moviescope.ui.favorites_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.data.repo.MovieScopeRepository
import com.example.moviescope.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    repository : MovieScopeRepository
) : ViewModel() {

    val fetchMediaFromBookmarks : StateFlow<Resource<List<BookmarkEntity>>> =
        repository.fetchMediaFromBookmarks().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Resource.Loading
        )
}