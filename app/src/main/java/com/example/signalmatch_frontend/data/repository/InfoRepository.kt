package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.InfoApi
import com.example.signalmatch_frontend.data.local.TokenHolder
import com.example.signalmatch_frontend.data.model.response.InvestorInfoResponse
import com.example.signalmatch_frontend.data.model.response.StartupInfoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InfoRepository @Inject constructor(
    private val infoApi: InfoApi
) {
    suspend fun getStartupInfo(userId: Int): StartupInfoResponse {
        val token = TokenHolder.accessToken
            ?: throw IllegalStateException("AccessToken is null.")
        return infoApi.getStartupInfo(
            userId = userId,
            token = "Bearer $token"
        )
    }

    suspend fun getInvestorInfo(userId: Int): InvestorInfoResponse {
        val token = TokenHolder.accessToken
            ?: throw IllegalStateException("AccessToken is null.")
        return infoApi.getInvestorInfo(
            userId = userId,
            token = "Bearer $token"
        )
    }
}

