package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.ProfileApi
import com.example.signalmatch_frontend.data.model.request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.request.InvestorEditProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupEditProfileRequest
import com.example.signalmatch_frontend.data.model.response.GetInvestorProfileResponse
import com.example.signalmatch_frontend.data.model.response.GetStartupProfileResponse
import com.example.signalmatch_frontend.data.model.response.InvestorCreateProfileResponse
import com.example.signalmatch_frontend.data.model.response.InvestorEditProfileResponse
import com.example.signalmatch_frontend.data.model.response.StartupCreateProfileResponse
import com.example.signalmatch_frontend.data.model.response.StartupEditProfileResponse
import retrofit2.HttpException
import retrofit2.Response
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

    suspend fun editInvestorProfile(
        req: InvestorEditProfileRequest
    ): Response<InvestorEditProfileResponse> {
        return profileApi.editInvestorProfile(req)
    }

    suspend fun startupCreateProfile(req: StartupCreateProfileRequest): StartupCreateProfileResponse {
        return profileApi.startupCreateProfile(req)
    }

    suspend fun getStartupProfile(
        userId: Int
    ): GetStartupProfileResponse {
        return profileApi.getStartupProfile(userId)
    }

    suspend fun editStartupProfile(
        req: StartupEditProfileRequest
    ): Response<StartupEditProfileResponse> {
        return profileApi.editStartupProfile(req)
    }

}
