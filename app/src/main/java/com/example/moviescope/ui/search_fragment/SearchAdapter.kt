package com.example.moviescope.ui.search_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviescope.databinding.ItemSeeAllBinding
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Constants
import com.example.moviescope.util.loadImage

class SearchAdapter(

) : PagingDataAdapter<MovieUI, SearchAdapter.ItemMoviesViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSeeAllBinding.inflate(layoutInflater, parent, false)
        return ItemMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemMoviesViewHolder, position: Int) {
        getItem(position)?.let { movieUI ->
            holder.bind(movieUI)
        }
    }

    inner class ItemMoviesViewHolder(private val binding: ItemSeeAllBinding) :
        ViewHolder(binding.root) {
        fun bind(movieUI: MovieUI) {
            binding.imageView.loadImage(
                Constants.BASE_URL_IMAGE.plus(
                    movieUI.posterPath ?: movieUI.backdropPath
                )
            )
        }
    }

    object Comparator : DiffUtil.ItemCallback<MovieUI>() {
        override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem == newItem
        }

    }
}