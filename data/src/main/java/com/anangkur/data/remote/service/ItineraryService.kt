package com.anangkur.data.remote.service

import retrofit2.http.GET

interface ItineraryService {
    @GET("itinerary")
    fun getAllItinerary(): List<Any>
}
