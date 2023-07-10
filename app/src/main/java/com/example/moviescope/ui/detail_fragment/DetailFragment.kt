package com.example.moviescope.ui.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.moviescope.databinding.FragmentDetailBinding
import com.example.moviescope.domain.mapper.toBookmarkEntity
import com.example.moviescope.util.Constants
import com.example.moviescope.util.getReformatDate
import com.example.moviescope.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
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
            posterMovieImageView.loadImage(Constants.BASE_URL_IMAGE.plus(args.movieUI.backdropPath))
            titleMovieTextView.text = args.movieUI.title
            rateTextView.text = args.movieUI.voteAverage.toString()
            yearTextView.text = getReformatDate(args.movieUI.releaseDate)
            prologTextView.text = args.movieUI.overview
            viewModel.initIsBookmarked(args.movieUI.id)

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.isBookmarked.collectLatest {
                        likeIconButton.isChecked = it
                    }
                }
            }

            likeIconButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.insertMediaToBookmarks(args.movieUI.toBookmarkEntity())
                } else {
                    viewModel.deleteMediaFromBookmarks(args.movieUI.toBookmarkEntity())
                }
            }
        }
    }
}