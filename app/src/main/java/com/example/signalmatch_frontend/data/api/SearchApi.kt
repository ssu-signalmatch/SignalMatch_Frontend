package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.response.BestStartupResponse
import com.example.signalmatch_frontend.data.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("api/search")
    suspend fun search(
        @Query("keyword") keyword: String,
        @Query("areas") areas: List<String>?,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: List<String> = emptyList()
    ): SearchResponse

    @GET("api/search/best")
    suspend fun getBestStartups(): BestStartupResponse
}
