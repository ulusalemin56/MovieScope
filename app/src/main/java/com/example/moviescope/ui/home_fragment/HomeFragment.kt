package com.example.moviescope.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviescope.R
import com.example.moviescope.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)


        initRecylerView()


        return binding.root
    }

    private fun initRecylerView() {
        val imageMovieModels = getListImageMovie()
        val adapter = ImageMovieAdapter(imageMovieModels)
        binding.apply {
            mostPopMoviesRecyclerView.adapter = adapter
            topRatedMoviesRecyclerView.adapter = adapter
            mostPopSeriesRecyclerView.adapter = adapter
            topBoxofficeRecyclerView.adapter = adapter
        }
    }

    private fun getListImageMovie(): List<ImageMovieModel> {
        return mutableListOf(
            ImageMovieModel(R.drawable.deneme_poster),
            ImageMovieModel(R.drawable.deneme_poster2),
            ImageMovieModel(R.drawable.deneme_poster3),
            ImageMovieModel(R.drawable.deneme_poster),
            ImageMovieModel(R.drawable.deneme_poster2),
            ImageMovieModel(R.drawable.deneme_poster3),
            ImageMovieModel(R.drawable.deneme_poster),
            ImageMovieModel(R.drawable.deneme_poster2),
            ImageMovieModel(R.drawable.deneme_poster3),
        )
    }

}