package com.example.signalmatch_frontend.data.model.response

data class GetBookmarkResponse(
    val message: String,
    val data: List<TargetItem>,
    val success: Boolean
)

data class TargetItem(
    val targetType: String,
    val targetTypeId: Int,
    val targetUserId: Int,
    val name: String
)