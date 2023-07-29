package com.example.moviescope.ui.seeall_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviescope.databinding.FragmentSeeAllBinding
import com.example.moviescope.util.enums.MediaTypeEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllFragment : Fragment() {
    private lateinit var binding: FragmentSeeAllBinding
    private val args: SeeAllFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)

        val mediaType = args.mediaType
        when (mediaType) {
            MediaTypeEnum.TOP_RATED_MOVIES -> {

            }

            MediaTypeEnum.NOW_PLAYING_MOVIES -> {

            }

            MediaTypeEnum.POPULAR_TV_SERIES -> {

            }

            MediaTypeEnum.TOP_RATED_TV_SERIES -> {

            }
        }

        return binding.root
    }

}