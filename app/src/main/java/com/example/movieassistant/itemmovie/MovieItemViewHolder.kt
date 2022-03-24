package com.example.movieassistant.itemmovie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.ClickListener
import com.example.movieassistant.R

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var poster: ImageView = itemView.findViewById(R.id.poster)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val overview: TextView = itemView.findViewById(R.id.overview)

    fun bind(item: MovieItem, listener: ClickListener) {
        poster.setImageResource(item.imageId)
        title.text = item.title
        overview.text = item.overview

        itemView.setOnLongClickListener {
            listener.onLongClick(item)
            return@setOnLongClickListener true
        }

        itemView.setOnClickListener {
            listener.onShortClick(item)
        }
    }
}
