package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.ProfileApi
import com.example.signalmatch_frontend.data.model.request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.model.response.GetInvestorProfileResponse
import com.example.signalmatch_frontend.data.model.response.GetStartupProfileResponse
import com.example.signalmatch_frontend.data.model.response.InvestorCreateProfileResponse
import com.example.signalmatch_frontend.data.model.response.StartupCreateProfileResponse
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi
) {
    suspend fun investorCreateProfile(req: InvestorCreateProfileRequest): InvestorCreateProfileResponse {
        return profileApi.investorCreateProfile(req)
    }

    suspend fun getInvestorProfile(
        userId: Int
    ): GetInvestorProfileResponse {
        return profileApi.getInvestorProfile(userId)
    }

    suspend fun startupCreateProfile(req: StartupCreateProfileRequest): StartupCreateProfileResponse {
        return profileApi.startupCreateProfile(req)
    }

    suspend fun getStartupProfile(
        userId: Int
    ): GetStartupProfileResponse {
        return profileApi.getStartupProfile(userId)
    }

}
