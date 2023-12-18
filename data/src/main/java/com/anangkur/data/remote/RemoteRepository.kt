package com.anangkur.data.remote

import com.anangkur.data.remote.response.toMovie
import com.anangkur.data.remote.service.ItineraryService
import com.anangkur.data.remote.service.TMDBService
import com.anangkur.domain.Itinerary
import com.anangkur.domain.Movie
import com.anangkur.domain.repository.ItineraryRepository
import com.anangkur.domain.repository.MovieRepository
import javax.inject.Inject

class RemoteRepository
    @Inject
    constructor(
        private val tmdbService: TMDBService,
        private val itineraryService: ItineraryService,
    ) : MovieRepository, ItineraryRepository {
        override suspend fun getAllItineraries(): List<Itinerary> {
            return itineraryService.getAllItinerary().map { Itinerary(0) }
        }

        override suspend fun fetchMovies(): List<Movie> {
            return tmdbService.fetchMovies().results?.map { it.toMovie() }.orEmpty()
        }
    }
