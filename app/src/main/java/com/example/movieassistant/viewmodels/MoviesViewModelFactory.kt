package com.example.movieassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieassistant.repositories.Repository

class MoviesViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(
            repository = repository,
        ) as T
    }
}
