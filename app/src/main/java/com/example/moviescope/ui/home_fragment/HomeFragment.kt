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
import com.example.moviescope.util.safeNavigate
import com.example.moviescope.util.showMotionToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle

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
                with(binding) {
                    with(viewModel) {
                        launch {
                            topRatedMovies.collectLatest {
                                when (it) {
                                    is Resource.Loading -> {
                                        topRatedMoviesContainerShimmer.visibility = View.VISIBLE
                                        topRatedMoviesContainerShimmer.startShimmer()
                                    }
                                    is Resource.Success -> {
                                        topRatedMoviesContainerShimmer.stopShimmer()
                                        topRatedMoviesContainerShimmer.visibility = View.GONE
                                        topRatedMoviesTextView.visibility = View.VISIBLE
                                        topRatedMoviesRV.visibility = View.VISIBLE
                                        initTopRatedMoviesRV(it.data)
                                    }

                                    is Resource.Error -> {}
                                }
                            }
                        }

                        launch {
                            nowPlayingMovies.collectLatest {
                                when (it) {
                                    is Resource.Loading -> {
                                        nowPlayingMoviesContainerShimmer.visibility = View.VISIBLE
                                        nowPlayingMoviesContainerShimmer.startShimmer()
                                    }
                                    is Resource.Success -> {
                                        nowPlayingMoviesContainerShimmer.stopShimmer()
                                        nowPlayingMoviesContainerShimmer.visibility = View.GONE
                                        nowPlayingMoviesTextView.visibility = View.VISIBLE
                                        nowPlayingMoviesRecyclerView.visibility = View.VISIBLE
                                        initNowPlayingMoviesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast("ERROR",
                                            it.throwable.localizedMessage ?: "Error",
                                            MotionToastStyle.ERROR)
                                    }
                                }
                            }
                        }

                        launch {
                            popularTvSeries.collectLatest {
                                when (it) {
                                    is Resource.Loading -> {
                                        popularTvSeriesContainerShimmer.visibility = View.VISIBLE
                                        popularTvSeriesContainerShimmer.startShimmer()
                                    }
                                    is Resource.Success -> {
                                        popularTvSeriesContainerShimmer.stopShimmer()
                                        popularTvSeriesContainerShimmer.visibility = View.GONE
                                        popularTvSeriesTextView.visibility = View.VISIBLE
                                        popularTvSeriesRecyclerView.visibility = View.VISIBLE
                                        initPopularTvSeriesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast("ERROR",
                                            it.throwable.localizedMessage ?: "Error",
                                            MotionToastStyle.ERROR)
                                    }
                                }
                            }

                        }

                        launch {
                            topRatedTvSeries.collect {
                                when (it) {
                                    is Resource.Loading -> {
                                        topRatedTvSeriesContainerShimmer.visibility = View.VISIBLE
                                        topRatedTvSeriesContainerShimmer.startShimmer()
                                    }
                                    is Resource.Success -> {
                                        topRatedTvSeriesContainerShimmer.stopShimmer()
                                        topRatedTvSeriesContainerShimmer.visibility = View.GONE
                                        topRatedTvSeriesTextView.visibility = View.VISIBLE
                                        topRatedTvSeriesRecyclerView.visibility = View.VISIBLE
                                        initTopRatedTvSeriesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast("ERROR",
                                        it.throwable.localizedMessage ?: "Error",
                                        MotionToastStyle.ERROR)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTopRatedMoviesRV(movies: List<MovieUI>) {
        binding.topRatedMoviesRV.adapter = getMovieAdapter(movies)
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
            findNavController().safeNavigate(action)
        }
    }

}