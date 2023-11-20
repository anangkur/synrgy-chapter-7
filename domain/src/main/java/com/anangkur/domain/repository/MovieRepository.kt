package com.anangkur.domain.repository

import com.anangkur.domain.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}