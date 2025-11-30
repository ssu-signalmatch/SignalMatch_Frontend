package com.example.signalmatch_frontend.data.model.response

data class GetBookmarkResponse(
    val message: String,
    val data: List<GetBookmarkData>,
    val success: Boolean
)

data class GetBookmarkData(
    val targetType: String,
    val targetId: Int,
    val name: String
)