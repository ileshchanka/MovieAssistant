package com.example.movieassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.MainActivity.Companion.FAVOURITE_LIST_EXTRA
import com.example.movieassistant.itemfavourite.FavouriteItemAdapter
import com.example.movieassistant.itemfavourite.FavouriteItemAdapter.ClickListener
import com.example.movieassistant.models.MovieItem

class FavouriteActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view_favourites) }
    private var items: MutableList<MovieItem> = mutableListOf()
    private val result = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        intent?.let {
            items = it.getParcelableArrayListExtra(FAVOURITE_LIST_EXTRA) ?: mutableListOf()
        }
        recyclerView.adapter = FavouriteItemAdapter(items, object : ClickListener {
            override fun onShortClick(movieItem: MovieItem) {
                Toast.makeText(this@FavouriteActivity, "Movie Click", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(movieItem: MovieItem, position: Int) {
                items.remove(movieItem)
                recyclerView.adapter?.notifyItemRemoved(position)
                result.apply {
                    putParcelableArrayListExtra(FAVOURITE_LIST_EXTRA, items as ArrayList<MovieItem>)
                }
                setResult(RESULT_CANCELED, result)
                Toast.makeText(this@FavouriteActivity, resources.getString(R.string.removed_from_favourite), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
