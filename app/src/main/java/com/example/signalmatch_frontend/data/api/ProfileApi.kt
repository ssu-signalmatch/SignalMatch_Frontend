package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.Request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Response.InvestorCreateProfileResponse
import com.example.signalmatch_frontend.data.model.Response.StartupCreateProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApi {

    @POST("/api/investors/profile")
    suspend fun investorCreateProfile(
        @Body body: InvestorCreateProfileRequest
    ):  InvestorCreateProfileResponse

    @POST("/api/startups/profile")
    suspend fun startupCreateProfile(
        @Body body: StartupCreateProfileRequest
    ): StartupCreateProfileResponse
}