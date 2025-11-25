package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.AuthApi
import com.example.signalmatch_frontend.data.model.request.LoginRequest
import com.example.signalmatch_frontend.data.model.request.SignupRequest
import com.example.signalmatch_frontend.data.model.response.LoginResponse
import com.example.signalmatch_frontend.data.model.response.SignoutResponse
import com.example.signalmatch_frontend.data.model.response.SignupResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(loginId: String, password: String): LoginResponse {
        return api.login(LoginRequest(loginId, password))
    }

    suspend fun signup(
        loginId: String,
        password: String,
        name: String,
        userRole: String
    ): SignupResponse {
        return api.signup(SignupRequest(loginId, password, name, userRole))
    }

    suspend fun signout(): SignoutResponse {
        return api.signout()
    }
}
