package com.anangkur.synrgychapter6.data.remote

import com.anangkur.synrgychapter6.data.remote.response.toMovie
import com.anangkur.synrgychapter6.data.remote.service.TMDBService
import com.anangkur.synrgychapter6.domain.Movie
import com.anangkur.synrgychapter6.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val tmdbService: TMDBService,
) : HomeRepository {
    override suspend fun fetchMovies(): List<Movie> {
        return tmdbService.fetchMovies().results?.map { result -> result.toMovie() }.orEmpty()
    }
}