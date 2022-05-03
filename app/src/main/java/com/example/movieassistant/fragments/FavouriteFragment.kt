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

class FavouriteFragment : Fragment(R.layout.fragment_favourites) {

    private val factory: MoviesViewModelFactory = MoviesViewModelFactory(RepositoryImpl())
    private val viewModel: MoviesViewModel by activityViewModels { factory }

    private val moviesAdapter = MovieItemAdapter(
        onShortMovieClick = { showDetails(it) },
        onLongMovieClick = { removeFromFavourites(it) },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_favourites)
        recyclerView.adapter = moviesAdapter

        viewModel.favouriteList.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }

    private fun removeFromFavourites(movie: Movie) {
        viewModel.onRemoveFromFavourites(movie)
        view?.let { Snackbar.make(it, resources.getString(R.string.removed_from_favourite), Snackbar.LENGTH_SHORT).show() }
    }

    private fun showDetails(movie: Movie) {
        findNavController().navigate(R.id.action_global_detailsFragment, bundleOf(MOVIE_BUNDLE to movie))
    }
}
