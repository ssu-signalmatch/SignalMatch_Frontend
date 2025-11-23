package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.Request.LoginRequest
import com.example.signalmatch_frontend.data.model.Request.SignupRequest
import com.example.signalmatch_frontend.data.model.Response.LoginResponse
import com.example.signalmatch_frontend.data.model.Response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/auth/signup")
    suspend fun signup(@Body request: SignupRequest): SignupResponse
}
