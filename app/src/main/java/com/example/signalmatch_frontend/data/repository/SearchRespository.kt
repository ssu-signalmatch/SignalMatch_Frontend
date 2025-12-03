package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.SearchApi
import com.example.signalmatch_frontend.data.model.request.SearchRequest
import com.example.signalmatch_frontend.data.model.response.BestStartupResponse
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun search(keyword: String) =
        searchApi.search(SearchRequest(keyword))

    suspend fun getBestStartups(): BestStartupResponse {
        return searchApi.getBestStartups()
    }
}