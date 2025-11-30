package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.SearchRequest
import com.example.signalmatch_frontend.data.model.response.SearchResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchApi {

    @POST("/api/search")
    suspend fun search(
        @Body request: SearchRequest
    ): SearchResponse
}
