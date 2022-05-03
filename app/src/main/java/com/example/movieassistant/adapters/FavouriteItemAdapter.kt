package com.example.movieassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.R
import com.example.movieassistant.models.Movie

class FavouriteItemAdapter(
    private val items: List<Movie>,
    private val listener: ClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavouriteItemViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavouriteItemViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ClickListener {
        fun onShortClick(movie: Movie)
        fun onLongClick(movie: Movie, position: Int)
    }

    inner class FavouriteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var poster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val overview: TextView = itemView.findViewById(R.id.overview)

        fun bind(item: Movie, listener: ClickListener) {
            poster.setImageResource(item.imageId)
            title.text = item.title
            overview.text = item.overview

            itemView.setOnLongClickListener {
                listener.onLongClick(item, layoutPosition)

                return@setOnLongClickListener true
            }

            itemView.setOnClickListener {
                listener.onShortClick(item)
            }
        }
    }

}
