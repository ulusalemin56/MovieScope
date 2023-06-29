package com.example.moviescope.ui.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviescope.databinding.FragmentDetailBinding
import com.example.moviescope.util.Constants
import com.example.moviescope.util.getReformatDate
import com.example.moviescope.util.loadImage

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        initUi()
        return binding.root
    }

    private fun initUi() {
        with(binding) {
            posterMovieImageView.loadImage(Constants.BASE_URL_IMAGE.plus(args.backdropPath))
            titleMovieTextView.text = args.title
            rateTextView.text = args.voteAverage.toString()
            yearTextView.text = getReformatDate(args.releaseDate)
            prologTextView.text = args.overvew
        }
    }
}