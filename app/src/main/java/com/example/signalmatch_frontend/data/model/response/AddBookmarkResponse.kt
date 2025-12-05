package com.example.signalmatch_frontend.data.model.response

data class AddBookmarkResponse(
    val message: String,
    val data: AddBookmarkData,
    val success: Boolean
)

data class AddBookmarkData(
    val bookmarkId: Long,
    val userId: Long,
    val targetUserId: Long
)