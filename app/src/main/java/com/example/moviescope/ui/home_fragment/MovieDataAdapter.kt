package com.example.moviescope.ui.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviescope.databinding.ItemImageBinding
import com.example.moviescope.domain.model.MovieUI
import com.example.moviescope.util.Constants

class MovieDataAdapter(
    private val movies: List<MovieUI>,
    private val onClickMovie : (position : Int) -> Unit
) : Adapter<MovieDataAdapter.ImageMovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDataAdapter.ImageMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(layoutInflater)
        return ImageMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDataAdapter.ImageMovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ImageMovieViewHolder(
        private val binding: ItemImageBinding
    ) : ViewHolder(binding.root) {
        fun bind(movie: MovieUI) {
            Glide.with(binding.root).load(Constants.BASE_URL_IMAGE.plus(movie.posterPath))
                .into(binding.imageView)

            binding.cardView.setOnClickListener {
                onClickMovie(adapterPosition)
            }
        }
    }
}