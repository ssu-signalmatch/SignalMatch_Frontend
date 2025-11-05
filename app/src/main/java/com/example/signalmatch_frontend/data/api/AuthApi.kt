package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.Request.LoginRequest
import com.example.signalmatch_frontend.data.model.Response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
