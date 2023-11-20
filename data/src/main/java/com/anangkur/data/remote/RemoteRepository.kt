package com.anangkur.data.remote

import com.anangkur.data.remote.response.toMovie
import com.anangkur.data.remote.service.TMDBService
import com.anangkur.domain.Movie
import com.anangkur.domain.repository.MovieRepository
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val tmdbService: TMDBService,
) : MovieRepository {
    override suspend fun fetchMovies(): List<Movie> {
        return tmdbService.fetchMovies().results?.map { result -> result.toMovie() }.orEmpty()
    }
}