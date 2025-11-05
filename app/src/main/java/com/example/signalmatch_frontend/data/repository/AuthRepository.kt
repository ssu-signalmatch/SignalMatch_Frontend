package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.AuthApi
import com.example.signalmatch_frontend.data.model.Request.LoginRequest
import com.example.signalmatch_frontend.data.model.Response.LoginResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(loginId: String, password: String): LoginResponse {
        return api.login(LoginRequest(loginId, password))
    }
}
