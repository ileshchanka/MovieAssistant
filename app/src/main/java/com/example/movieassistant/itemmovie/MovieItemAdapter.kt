package com.example.movieassistant.itemmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.ClickListener
import com.example.movieassistant.R

class MovieItemAdapter(
    private val items: List<MovieItem>,
    private val listener: ClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return MovieItemViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
