package com.example.moviescope.ui.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviescope.databinding.ItemImageBinding

class ImageMovieAdapter(private val imageModels : List<ImageMovieModel>) : Adapter<ImageMovieAdapter.ImageMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageMovieAdapter.ImageMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemImageBinding.inflate(layoutInflater)

        return ImageMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageMovieAdapter.ImageMovieViewHolder, position: Int) {
        holder.bind(imageModels[position])
    }

    override fun getItemCount(): Int {
        return imageModels.size
    }

    inner class ImageMovieViewHolder(private val binding : ItemImageBinding) : ViewHolder(binding.root) {

        fun bind(imageMovie : ImageMovieModel) {

            binding.imageView.setImageResource(imageMovie.image)
        }

    }
}