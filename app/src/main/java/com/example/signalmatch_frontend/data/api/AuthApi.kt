package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.LoginRequest
import com.example.signalmatch_frontend.data.model.request.SignupRequest
import com.example.signalmatch_frontend.data.model.response.LoginResponse
import com.example.signalmatch_frontend.data.model.response.SignoutResponse
import com.example.signalmatch_frontend.data.model.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.DELETE

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/auth/signup")
    suspend fun signup(@Body request: SignupRequest): SignupResponse

    @DELETE("api/auth")
    suspend fun signout(): SignoutResponse
}
