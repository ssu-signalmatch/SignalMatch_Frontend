package com.example.signalmatch_frontend.data.model.Response

data class LoginResponse(
    val message: String,
    val data: TokenData,
    val success: Boolean
)

data class TokenData(
    val accessToken: String
)
