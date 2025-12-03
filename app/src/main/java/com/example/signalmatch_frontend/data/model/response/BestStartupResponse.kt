package com.example.signalmatch_frontend.data.model.response

data class BestStartupResponse(
    val message: String,
    val data: List<BestStartupItem>,
    val success: Boolean
)

data class BestStartupItem(
    val startupId: Int,
    val startupName: String,
    val intro: String,
    val bookmarkCount: Int
)
