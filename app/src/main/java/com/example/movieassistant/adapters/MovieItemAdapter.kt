package com.example.movieassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.R
import com.example.movieassistant.adapters.MovieItemAdapter.MovieItemViewHolder
import com.example.movieassistant.models.Movie

class MovieItemAdapter(
    val onShortMovieClick: (movie: Movie) -> Unit,
    val onLongMovieClick: (movie: Movie) -> Unit,
) : ListAdapter<Movie, MovieItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title
            override fun getChangePayload(oldItem: Movie, newItem: Movie) = Any()
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieItemViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var poster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val overview: TextView = itemView.findViewById(R.id.overview)

        fun bind(item: Movie) {
            poster.setImageResource(item.imageId)
            title.text = item.title
            overview.text = item.overview

            itemView.setOnClickListener {
                onShortMovieClick(item)
            }

            itemView.setOnLongClickListener {
                onLongMovieClick(item)
                true
            }
        }
    }
}
