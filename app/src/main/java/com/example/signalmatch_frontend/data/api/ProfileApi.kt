package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.model.response.GetInvestorProfileResponse
import com.example.signalmatch_frontend.data.model.response.GetStartupProfileResponse
import com.example.signalmatch_frontend.data.model.response.InvestorCreateProfileResponse
import com.example.signalmatch_frontend.data.model.response.StartupCreateProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApi {

    @POST("/api/investors/me")
    suspend fun investorCreateProfile(
        @Body body: InvestorCreateProfileRequest
    ):  InvestorCreateProfileResponse

    @GET("/api/investors/{userId}")
    suspend fun getInvestorProfile(
        @Path("userId") userId: Long
    ): GetInvestorProfileResponse

    @POST("/api/startups/me")
    suspend fun startupCreateProfile(
        @Body body: StartupCreateProfileRequest
    ): StartupCreateProfileResponse

    @GET("/api/startups/{userId}")
    suspend fun getStartupProfile(
        @Path("userId") userId: Long
    ): GetStartupProfileResponse
}