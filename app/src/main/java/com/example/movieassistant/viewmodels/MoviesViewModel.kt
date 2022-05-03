package com.example.movieassistant.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieassistant.models.Movie
import com.example.movieassistant.repositories.Repository

class MoviesViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val favouriteListLiveData: MutableLiveData<List<Movie>> = MutableLiveData(listOf())
    val favouriteList: LiveData<List<Movie>>
        get() = favouriteListLiveData

    fun getMovies(): List<Movie> {
        return repository.getMovies()
    }

    fun onAddToFavourites(movie: Movie): Boolean {
        return if (favouriteListLiveData.value?.contains(movie)?.not() == true) {
            val favourites = favouriteListLiveData.value?.toMutableList()?.apply {
                add(movie)
            }?.toList()
            favouriteListLiveData.value = favourites
            true
        } else {
            false
        }
    }

    fun onRemoveFromFavourites(movie: Movie) {
        if (favouriteListLiveData.value?.contains(movie) == true) {
            val favourites = favouriteListLiveData.value?.toMutableList()?.apply {
                remove(movie)
            }?.toList()
            favouriteListLiveData.value = favourites
        }
    }
}
