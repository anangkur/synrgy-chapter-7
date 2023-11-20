package com.anangkur.synrgychapter6.data.remote.service

import com.anangkur.synrgychapter6.data.remote.response.Response

class FakeTMDBService : TMDBService {
    override suspend fun fetchMovies(apiKey: String, withOriginalLanguage: String): Response {
        return Response(page = 1, results = emptyList(), totalPages = 1, totalResults = 0)
    }
}