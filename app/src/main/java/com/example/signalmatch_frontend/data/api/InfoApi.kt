package com.example.signalmatch_frontend.data.api


import com.example.signalmatch_frontend.data.model.response.InvestorInfoResponse
import com.example.signalmatch_frontend.data.model.response.StartupInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface InfoApi {

    @GET("api/me/{userId}")
    suspend fun getStartupInfo(
        @Path("userId") userId: Int,
        @Header("Authorization") token: String
    ): StartupInfoResponse

    @GET("api/me/{userId}")
    suspend fun getInvestorInfo(
        @Path("userId") userId: Int,
        @Header("Authorization") token: String
    ): InvestorInfoResponse
}
