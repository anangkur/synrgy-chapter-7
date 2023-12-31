package com.anangkur.synrgychapter7.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel
    @Inject
    constructor(
        private val movieRepository: MovieRepository,
    ) : ViewModel() {
        private val _loading = MutableLiveData<Boolean>()
        val loading: LiveData<Boolean> = _loading

        private val _error = MutableLiveData<String?>()
        val error: LiveData<String?> = _error

        private val _movies = MutableLiveData<List<MovieUiData>>()
        val movies: LiveData<List<MovieUiData>> = _movies

        fun fetchMovies() {
            _loading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    movieRepository.fetchMovies()
                }.onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _error.value = exception.message
                    }
                }.onSuccess { movies ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _movies.value = movies.map { movie -> movie.toMovieUiData() }
                    }
                }
            }
        }

        private fun provideData(): List<MovieUiData> {
            return listOf(MovieUiData("", "", ""))
        }
    }
