package com.example.movieassistant.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.movieassistant.MOVIE_BUNDLE
import com.example.movieassistant.R
import com.example.movieassistant.models.Movie

class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.getParcelable<Movie>(MOVIE_BUNDLE)

        val imageView = view.findViewById<ImageView>(R.id.movie_image)
        movie?.imageId?.let {
            imageView.setImageResource(it)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = movie?.title

        val overview = view.findViewById<TextView>(R.id.movie_overview)
        overview.text = movie?.overview

    }
}
