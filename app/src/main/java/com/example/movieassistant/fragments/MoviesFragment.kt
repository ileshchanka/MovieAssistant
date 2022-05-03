package com.example.movieassistant.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.MOVIE_BUNDLE
import com.example.movieassistant.R
import com.example.movieassistant.adapters.MovieItemAdapter
import com.example.movieassistant.models.Movie
import com.example.movieassistant.repositories.RepositoryImpl
import com.example.movieassistant.viewmodels.MoviesViewModel
import com.example.movieassistant.viewmodels.MoviesViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val factory: MoviesViewModelFactory = MoviesViewModelFactory(RepositoryImpl())
    private val viewModel: MoviesViewModel by activityViewModels { factory }

    private val moviesAdapter = MovieItemAdapter(
        onShortMovieClick = { showDetails(it) },
        onLongMovieClick = { addToFavourites(it) },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter.submitList(viewModel.getMovies())

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_movies)
        recyclerView.adapter = moviesAdapter
    }

    private fun addToFavourites(movie: Movie) {
        val message = if (viewModel.onAddToFavourites(movie)) {
            resources.getString(R.string.added_to_favorite)
        } else {
            resources.getString(R.string.not_added_to_favorite)
        }
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    private fun showDetails(movie: Movie) {
        findNavController().navigate(R.id.action_global_detailsFragment, bundleOf(MOVIE_BUNDLE to movie))
    }
}
