package com.example.signalmatch_frontend.data.model.response

data class StartupInfoResponse(
    val message: String,
    val data: StartupInfoData,
    val success: Boolean
)

data class StartupInfoData(
    val profile: StartupInfo? = null,
    val bookmarkCount: Int = 0,
    val profileImageUrl: String? = null,
    val updatedAt: String? = null
)

data class StartupInfo(
    val startupName: String,
    val status: String,
    val foundingDate: String,
    val address: String,
    val homepageUrl: String,
    val contactEmail: String,
    val intro: String,
    val representativeName: String,
    val businessNumber: String,
    val employeeCount: Int,
    val legalType: String,
    val scale: String,
    val revenue: Long,
    val profit: Long,
    val fundingRounds: Int,
    val totalFunding: Long,
    val investorStages: String,
    val businessAreas: List<String>,
    val history: String
)