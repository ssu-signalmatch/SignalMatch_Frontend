package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.SearchApi
import com.example.signalmatch_frontend.data.model.response.BestStartupResponse
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun search(
        keyword: String,
        areas: List<String>,
        page: Int = 0,
        size: Int = 10,
        sort: List<String> = emptyList()
    ) = searchApi.search(
        keyword = keyword,
        areas = areas.takeIf { it.isNotEmpty() },
        page = page,
        size = size,
        sort = sort
    )

    suspend fun getBestStartups(): BestStartupResponse {
        return searchApi.getBestStartups()
    }
}


