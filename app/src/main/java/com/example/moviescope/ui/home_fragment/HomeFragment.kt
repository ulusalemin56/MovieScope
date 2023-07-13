package com.example.moviescope.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviescope.databinding.FragmentHomeBinding
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        initCollect()
        return binding.root
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewModel) {
                    launch {
                        topRatedMovies.collectLatest {
                            when (it) {
                                is Resource.Loading -> {}
                                is Resource.Success -> {
                                    initTopRatedMoviesRV(it.data)
                                }

                                is Resource.Error -> {}
                            }
                        }
                    }

                    launch {
                        nowPlayingMovies.collectLatest {
                            when (it) {
                                is Resource.Loading -> {}
                                is Resource.Success -> {
                                    initNowPlayingMoviesRV(it.data)
                                }

                                is Resource.Error -> {}
                            }
                        }
                    }

                    launch {
                        popularTvSeries.collectLatest {
                            when (it) {
                                is Resource.Loading -> {}
                                is Resource.Success -> {
                                    initPopularTvSeriesRV(it.data)
                                }

                                is Resource.Error -> {}
                            }
                        }

                    }

                    launch {
                        topRatedTvSeries.collect {
                            when (it) {
                                is Resource.Loading -> {}
                                is Resource.Success -> {
                                    initTopRatedTvSeriesRV(it.data)
                                }

                                is Resource.Error -> {}
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTopRatedMoviesRV(movies: List<MovieUI>) {
        binding.topRatedMovies.adapter = getMovieAdapter(movies)
    }

    private fun initNowPlayingMoviesRV(movies: List<MovieUI>) {
        binding.nowPlayingMoviesRecyclerView.adapter = getMovieAdapter(movies)
    }

    private fun initPopularTvSeriesRV(series: List<MovieUI>) {
        binding.popularTvSeriesRecyclerView.adapter = getMovieAdapter(series)
    }

    private fun initTopRatedTvSeriesRV(series: List<MovieUI>) {
        binding.topRatedTvSeriesRecyclerView.adapter = getMovieAdapter(series)
    }

    private fun getMovieAdapter(movies: List<MovieUI>): MovieDataAdapter {
        return MovieDataAdapter(movies) { position ->
            val movieUI = movies[position]
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                movieUI
            )
            findNavController().navigate(action)
        }
    }

}