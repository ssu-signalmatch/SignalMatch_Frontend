package com.example.signalmatch_frontend.data.model.request

data class SearchRequest(
    val keyword: String,
    val areas: List<String> = emptyList(),
    val page: Int = 0,
    val size: Int = 10,
    val sort: List<String> = emptyList()
)

