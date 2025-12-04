package com.example.signalmatch_frontend.data.api

import retrofit2.http.GET
import com.example.signalmatch_frontend.data.model.response.MyPageResponse

interface UserApi {
    @GET("/api/me")
    suspend fun getMe(): MyPageResponse
}
