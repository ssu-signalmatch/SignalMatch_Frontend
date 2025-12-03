package com.example.signalmatch_frontend.data.model.response

data class InvestorEditProfileResponse(
    val message: String?,
    val data: InvestorEditData,
    val success: Boolean
    )

data class InvestorEditData(
    val startupId: String,
    val updatedAt: String
)
