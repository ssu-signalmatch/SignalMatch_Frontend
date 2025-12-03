package com.example.signalmatch_frontend.data.model.response

data class StartupEditProfileResponse(
    val message: String?,
    val data: StartupEditData,
    val success: Boolean
)

data class StartupEditData(
    val startupId: String,
    val updatedAt: String
)