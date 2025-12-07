package com.example.signalmatch_frontend.data.model.response

data class ApiSuccessGlobalResponse<t_body> (
    val message: String,
    val data: t_body?,
    val success: Boolean
)