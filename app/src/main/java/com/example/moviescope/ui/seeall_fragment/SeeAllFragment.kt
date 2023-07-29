package com.example.moviescope.ui.seeall_fragment

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
import androidx.paging.LoadState
import com.example.moviescope.databinding.FragmentSeeAllBinding
import com.example.moviescope.util.enums.MediaTypeEnum
import com.example.moviescope.util.showMotionToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class SeeAllFragment : Fragment() {
    private lateinit var binding: FragmentSeeAllBinding
    private val viewModel: SeeAllViewModel by viewModels()
    private val args: SeeAllFragmentArgs by navArgs()
    private val adapter = SeeAllAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        initCollect()
        return binding.root
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewModel) {
                    with(binding) {
                        seeAllRV.adapter = adapter
                        when (args.mediaType) {
                            MediaTypeEnum.TOP_RATED_MOVIES -> {
                                seeAllTopRatedMovies.collectLatest { pagingData ->
                                    adapter.submitData(lifecycle, pagingData)
                                    adapter.loadStateFlow.collectLatest { loadState ->
                                        when (loadState.refresh) {
                                            is LoadState.Loading -> {
                                                seeAllContainerShimmer.visibility = View.VISIBLE
                                                seeAllContainerShimmer.startShimmer()
                                                seeAllRV.visibility = View.GONE
                                            }

                                            is LoadState.NotLoading -> {
                                                seeAllContainerShimmer.stopShimmer()
                                                seeAllContainerShimmer.visibility = View.GONE
                                                seeAllRV.visibility = View.VISIBLE
                                            }

                                            is LoadState.Error -> {
                                                requireActivity().showMotionToast(
                                                    title = "ERROR",
                                                    description = (loadState.refresh as LoadState.Error).error.localizedMessage
                                                        ?: "Error",
                                                    motionStyle = MotionToastStyle.ERROR
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            MediaTypeEnum.NOW_PLAYING_MOVIES -> {
                                seeAllNowPlayingMovies.collectLatest { pagingData ->
                                    adapter.submitData(lifecycle, pagingData)
                                    adapter.loadStateFlow.collectLatest { loadState ->
                                        when (loadState.refresh) {
                                            is LoadState.Loading -> {
                                                seeAllContainerShimmer.visibility = View.VISIBLE
                                                seeAllContainerShimmer.startShimmer()
                                                seeAllRV.visibility = View.GONE
                                            }

                                            is LoadState.NotLoading -> {
                                                seeAllContainerShimmer.stopShimmer()
                                                seeAllContainerShimmer.visibility = View.GONE
                                                seeAllRV.visibility = View.VISIBLE
                                            }

                                            is LoadState.Error -> {
                                                requireActivity().showMotionToast(
                                                    title = "ERROR",
                                                    description = (loadState.refresh as LoadState.Error).error.localizedMessage
                                                        ?: "Error",
                                                    motionStyle = MotionToastStyle.ERROR
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            MediaTypeEnum.POPULAR_TV_SERIES -> {
                                seeAllPopularTvSeries.collectLatest { pagingData ->
                                    adapter.submitData(lifecycle, pagingData)
                                    adapter.loadStateFlow.collectLatest { loadState ->
                                        when (loadState.refresh) {
                                            is LoadState.Loading -> {
                                                seeAllContainerShimmer.visibility = View.VISIBLE
                                                seeAllContainerShimmer.startShimmer()
                                                seeAllRV.visibility = View.GONE
                                            }

                                            is LoadState.NotLoading -> {
                                                seeAllContainerShimmer.stopShimmer()
                                                seeAllContainerShimmer.visibility = View.GONE
                                                seeAllRV.visibility = View.VISIBLE
                                            }

                                            is LoadState.Error -> {
                                                requireActivity().showMotionToast(
                                                    title = "ERROR",
                                                    description = (loadState.refresh as LoadState.Error).error.localizedMessage
                                                        ?: "Error",
                                                    motionStyle = MotionToastStyle.ERROR
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            MediaTypeEnum.TOP_RATED_TV_SERIES -> {
                                seeAllTopRatedTvSeries.collectLatest { pagingData ->
                                    adapter.submitData(lifecycle, pagingData)
                                    adapter.loadStateFlow.collectLatest { loadState ->
                                        when (loadState.refresh) {
                                            is LoadState.Loading -> {
                                                seeAllContainerShimmer.visibility = View.VISIBLE
                                                seeAllContainerShimmer.startShimmer()
                                                seeAllRV.visibility = View.GONE
                                            }

                                            is LoadState.NotLoading -> {
                                                seeAllContainerShimmer.stopShimmer()
                                                seeAllContainerShimmer.visibility = View.GONE
                                                seeAllRV.visibility = View.VISIBLE
                                            }

                                            is LoadState.Error -> {
                                                requireActivity().showMotionToast(
                                                    title = "ERROR",
                                                    description = (loadState.refresh as LoadState.Error).error.localizedMessage ?: "Error",
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
        }
    }

}