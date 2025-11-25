package com.example.signalmatch_frontend.data.model.response

data class LoginResponse(
    val message: String,
    val data: TokenData,
    val success: Boolean
)

data class TokenData(
    val accessToken: String
)
