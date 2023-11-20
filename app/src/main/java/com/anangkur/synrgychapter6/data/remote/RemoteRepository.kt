package com.anangkur.synrgychapter6.data.remote

import com.anangkur.synrgychapter6.data.remote.response.toMovie
import com.anangkur.synrgychapter6.data.remote.service.TMDBService
import com.anangkur.synrgychapter6.domain.Movie
import com.anangkur.synrgychapter6.domain.repository.MovieRepository
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val tmdbService: TMDBService,
) : MovieRepository {
    override suspend fun fetchMovies(): List<Movie> {
        return tmdbService.fetchMovies().results?.map { result -> result.toMovie() }.orEmpty()
    }
}