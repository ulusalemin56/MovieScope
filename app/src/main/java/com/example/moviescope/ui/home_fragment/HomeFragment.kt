package com.example.moviescope.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviescope.data.model.movie.Movie
import com.example.moviescope.data.model.series.Series
import com.example.moviescope.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        initObserve()

        return binding.root
    }

    private fun initObserve() {
        with(viewModel) {
            topRatedMovies.observe(viewLifecycleOwner) {
                initTopRatedMoviesRV(it)
            }

            nowPlayingMovies.observe(viewLifecycleOwner) {
                initNowPlayingMoviesRV(it)
            }

            popularTvSeries.observe(viewLifecycleOwner) {
                initPopularTvSeries(it)
            }

            topRatedTvSeries.observe(viewLifecycleOwner) {
                initTopRatedTvSeries(it)
            }
        }
    }

    private fun initTopRatedMoviesRV(movies: List<Movie>) {

        val adapter = MovieDataAdapter(movies)
        binding.apply {
            topRatedMovies.adapter = adapter
        }
    }

    private fun initNowPlayingMoviesRV(movies: List<Movie>) {
        val adapter = MovieDataAdapter(movies)
        with(binding) {
            nowPlayingMoviesRecyclerView.adapter = adapter
        }
    }

    private fun initPopularTvSeries(series: List<Series>) {
        val adapter = SeriesDataAdapter(series)
        with(binding) {
            popularTvSeriesRecyclerView.adapter = adapter
        }
    }

    private fun initTopRatedTvSeries(series: List<Series>) {
        val adapter = SeriesDataAdapter(series)
        with(binding) {
            topRatedTvSeriesRecyclerView.adapter = adapter
        }
    }
}