package com.example.moviescope.ui.favorites_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviescope.data.model.local.BookmarkEntity
import com.example.moviescope.databinding.ItemFavoritesBinding
import com.example.moviescope.util.Constants
import com.example.moviescope.util.getReformatDate
import com.example.moviescope.util.loadImage

class FavoritesAdapter(
    private val bookmarks: List<BookmarkEntity>,
    private val onClick : (BookmarkEntity) -> Unit
) : Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoritesBinding.inflate(layoutInflater, parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(bookmarks[position])
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }

    inner class FavoritesViewHolder(
        private val binding: ItemFavoritesBinding
    ) : ViewHolder(binding.root) {

        fun bind(bookmark: BookmarkEntity) {
            with(binding) {
                imageView.loadImage(Constants.BASE_URL_IMAGE.plus(bookmark.posterPath ?: bookmark.backdropPath))
                titleMovieTextView.text = bookmark.title
                rateTextView.text = bookmark.voteAverage.toString()
                yearTextView.text = getReformatDate(bookmark.releaseDate)
                prologTextView.text = bookmark.overview

                itemCardView.setOnClickListener {
                    onClick(bookmark)
                }
            }
        }
    }
}