package com.example.movieassistant.itemfavourite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.ClickListener
import com.example.movieassistant.MainActivity.Companion.FAVOURITE_LIST_EXTRA
import com.example.movieassistant.R
import com.example.movieassistant.itemmovie.MovieItem

class FavouriteActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view_favourites) }
    private var items: MutableList<MovieItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        intent?.let {
            items = it.getParcelableArrayListExtra(FAVOURITE_LIST_EXTRA) ?: mutableListOf()
        }
        recyclerView.adapter = FavouriteItemAdapter(items, object : ClickListener {
            override fun onShortClick(movieItem: MovieItem) {
                TODO("Not yet implemented")
            }

            override fun onLongClick(movieItem: MovieItem) {
                items.remove(movieItem)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
