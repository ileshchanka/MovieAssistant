package com.example.movieassistant

import com.example.movieassistant.itemmovie.MovieItem

interface ClickListener {
    fun onShortClick(movieItem: MovieItem)
    fun onLongClick(movieItem: MovieItem)
}
