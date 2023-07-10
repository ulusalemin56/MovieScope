package com.example.moviescope.ui.favorites_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.databinding.FragmentFavoritesBinding
import com.example.moviescope.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        initCollect()
        return binding.root
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchMediaFromBookmarks.collectLatest {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                           initFavoritesRV(it.data)
                        }

                        is Resource.Error -> {}
                    }
                }
            }
        }
    }

    private fun initFavoritesRV(bookmarks: List<BookmarkEntity>) {

    }
}