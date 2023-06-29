package com.example.moviescope.ui.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviescope.databinding.ItemImageBinding
import com.example.moviescope.domain.model.SeriesUI
import com.example.moviescope.util.Constants
import com.example.moviescope.util.loadImage

class SeriesDataAdapter(
    private val series: List<SeriesUI>,
    private val onClickMovie: (position: Int) -> Unit
) : RecyclerView.Adapter<SeriesDataAdapter.SeriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesDataAdapter.SeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(layoutInflater)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesDataAdapter.SeriesViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int {
        return series.size
    }

    inner class SeriesViewHolder(
        private val binding: ItemImageBinding
    ) : ViewHolder(binding.root) {
        fun bind(series: SeriesUI) {
            with(binding) {
                imageView.loadImage(Constants.BASE_URL_IMAGE.plus(series.posterPath))
                cardView.setOnClickListener {
                    onClickMovie(adapterPosition)
                }
            }
        }
    }
}