package com.example.signalmatch_frontend.data.model.response

data class AddBookmarkResponse(
    val message: String,
    val data: List<AddBookmarkData>,
    val success: Boolean
)

data class AddBookmarkData(
    val bookmarkId: Int,
    val investorId: Int,
    val startupId: Int
)