package com.example.signalmatch_frontend.data.model.response

data class SignupResponse(
    val message: String,
    val data: Any?,
    val success: Boolean
)

data class ErrorResponse(
    val status: String,
    val message: String
)