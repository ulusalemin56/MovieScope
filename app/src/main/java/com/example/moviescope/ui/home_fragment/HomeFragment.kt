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
import com.example.moviescope.util.enums.MediaTypeEnum
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
        initUI()
        return binding.root
    }

    private fun initUI() {
        with(binding) {
            searchIconButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                findNavController().navigate(action)
            }

            seeAllTopRatedMovies.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MediaTypeEnum.TOP_RATED_MOVIES)
                findNavController().navigate(action)
            }

            seeAllNowPlayingMovies.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MediaTypeEnum.NOW_PLAYING_MOVIES)
                findNavController().navigate(action)
            }

            seeAllPopularTvSeries.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MediaTypeEnum.POPULAR_TV_SERIES)
                findNavController().navigate(action)
            }

            seeAllTopRatedTvSeries.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MediaTypeEnum.TOP_RATED_TV_SERIES)
                findNavController().navigate(action)
            }
        }
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
                                        topRatedMoviesTextView.visibility = View.GONE
                                        seeAllTopRatedMovies.visibility = View.GONE
                                        topRatedMoviesRV.visibility = View.GONE
                                        topRatedMoviesContainerShimmer.visibility = View.VISIBLE
                                        topRatedMoviesContainerShimmer.startShimmer()
                                    }

                                    is Resource.Success -> {
                                        topRatedMoviesContainerShimmer.stopShimmer()
                                        topRatedMoviesContainerShimmer.visibility = View.GONE
                                        topRatedMoviesTextView.visibility = View.VISIBLE
                                        seeAllTopRatedMovies.visibility = View.VISIBLE
                                        topRatedMoviesRV.visibility = View.VISIBLE
                                        initTopRatedMoviesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast(
                                            title = "ERROR",
                                            description = it.throwable.localizedMessage ?: "Error",
                                            motionStyle = MotionToastStyle.ERROR
                                        )
                                    }
                                }
                            }
                        }

                        launch {
                            nowPlayingMovies.collectLatest {
                                when (it) {
                                    is Resource.Loading -> {
                                        nowPlayingMoviesTextView.visibility = View.GONE
                                        seeAllNowPlayingMovies.visibility = View.GONE
                                        nowPlayingMoviesRecyclerView.visibility = View.GONE
                                        nowPlayingMoviesContainerShimmer.visibility = View.GONE
                                        nowPlayingMoviesContainerShimmer.startShimmer()
                                    }

                                    is Resource.Success -> {
                                        nowPlayingMoviesContainerShimmer.stopShimmer()
                                        nowPlayingMoviesContainerShimmer.visibility = View.GONE
                                        nowPlayingMoviesTextView.visibility = View.VISIBLE
                                        seeAllNowPlayingMovies.visibility = View.VISIBLE
                                        nowPlayingMoviesRecyclerView.visibility = View.VISIBLE
                                        initNowPlayingMoviesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast(
                                            title = "ERROR",
                                            description = it.throwable.localizedMessage ?: "Error",
                                            motionStyle = MotionToastStyle.ERROR
                                        )
                                    }
                                }
                            }
                        }

                        launch {
                            popularTvSeries.collectLatest {
                                when (it) {
                                    is Resource.Loading -> {
                                        popularTvSeriesTextView.visibility = View.GONE
                                        seeAllPopularTvSeries.visibility = View.GONE
                                        popularTvSeriesRecyclerView.visibility = View.GONE
                                        popularTvSeriesContainerShimmer.visibility = View.VISIBLE
                                        popularTvSeriesContainerShimmer.startShimmer()
                                    }

                                    is Resource.Success -> {
                                        popularTvSeriesContainerShimmer.stopShimmer()
                                        popularTvSeriesContainerShimmer.visibility = View.GONE
                                        popularTvSeriesTextView.visibility = View.VISIBLE
                                        seeAllPopularTvSeries.visibility = View.VISIBLE
                                        popularTvSeriesRecyclerView.visibility = View.VISIBLE
                                        initPopularTvSeriesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast(
                                            title = "ERROR",
                                            description = it.throwable.localizedMessage ?: "Error",
                                            motionStyle = MotionToastStyle.ERROR
                                        )
                                    }
                                }
                            }

                        }

                        launch {
                            topRatedTvSeries.collect {
                                when (it) {
                                    is Resource.Loading -> {
                                        topRatedTvSeriesTextView.visibility = View.GONE
                                        seeAllTopRatedTvSeries.visibility = View.GONE
                                        topRatedTvSeriesRecyclerView.visibility = View.GONE
                                        topRatedTvSeriesContainerShimmer.visibility = View.VISIBLE
                                        topRatedTvSeriesContainerShimmer.startShimmer()
                                    }

                                    is Resource.Success -> {
                                        topRatedTvSeriesContainerShimmer.stopShimmer()
                                        topRatedTvSeriesContainerShimmer.visibility = View.GONE
                                        topRatedTvSeriesTextView.visibility = View.VISIBLE
                                        seeAllTopRatedTvSeries.visibility = View.VISIBLE
                                        topRatedTvSeriesRecyclerView.visibility = View.VISIBLE
                                        initTopRatedTvSeriesRV(it.data)
                                    }

                                    is Resource.Error -> {
                                        requireActivity().showMotionToast(
                                            title = "ERROR",
                                            description = it.throwable.localizedMessage ?: "Error",
                                            motionStyle = MotionToastStyle.ERROR
                                        )
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