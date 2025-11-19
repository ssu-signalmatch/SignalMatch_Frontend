package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.ProfileApi
import com.example.signalmatch_frontend.data.model.Request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Response.InvestorCreateProfileResponse
import com.example.signalmatch_frontend.data.model.Response.StartupCreateProfileResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi
) {
    suspend fun investorCreateProfile(req: InvestorCreateProfileRequest): InvestorCreateProfileResponse {
        return profileApi.investorCreateProfile(req)
    }

    suspend fun startupCreateProfile(req: StartupCreateProfileRequest): StartupCreateProfileResponse {
        return profileApi.startupCreateProfile(req)
    }
}
