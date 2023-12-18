package com.anangkur.data.remote.service

class FakeItineraryService : ItineraryService {
    override fun getAllItinerary(): List<Any> {
        return emptyList()
    }
}
