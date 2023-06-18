package com.example.moviescope.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviescope.data.model.Movie
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

        viewModel.getTopRatedMovies()

        viewModel.movie.observe(viewLifecycleOwner) {
            initRecylerView(it.results)
        }


        return binding.root
    }

    private fun initRecylerView(movies: List<Movie>) {

        val adapter = ImageMovieAdapter(movies)
        binding.apply {
            mostPopMoviesRecyclerView.adapter = adapter
            topRatedMoviesRecyclerView.adapter = adapter
            mostPopSeriesRecyclerView.adapter = adapter
            topBoxofficeRecyclerView.adapter = adapter
        }
    }


}