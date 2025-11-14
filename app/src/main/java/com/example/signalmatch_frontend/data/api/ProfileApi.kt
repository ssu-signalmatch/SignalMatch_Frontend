package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.Request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Response.InvestorCreateProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApi {
    @POST("/api/investors/profile")
    suspend fun createInvestorProfile(
        @Body body: InvestorCreateProfileRequest
    ):  InvestorCreateProfileResponse
}