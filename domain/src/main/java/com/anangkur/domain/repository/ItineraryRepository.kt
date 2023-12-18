package com.anangkur.domain.repository

import com.anangkur.domain.Itinerary

interface ItineraryRepository {
    suspend fun getAllItineraries(): List<Itinerary>
}
