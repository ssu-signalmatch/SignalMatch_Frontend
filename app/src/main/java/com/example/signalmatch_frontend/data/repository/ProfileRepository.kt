package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.ProfileApi
import com.example.signalmatch_frontend.data.model.Request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Response.InvestorCreateProfileResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileApi: ProfileApi
) {
    suspend fun createProfile(req: InvestorCreateProfileRequest): InvestorCreateProfileResponse {
        return profileApi.createInvestorProfile(req)
    }
}
