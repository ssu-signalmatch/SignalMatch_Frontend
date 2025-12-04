package com.example.signalmatch_frontend.data.model.response

data class InvestorInfoResponse(
    val message: String,
    val data: InvestorInfoData,
    val success: Boolean
)

data class InvestorInfoData(
    val profile: InvestorInfo? = null,
    val bookmarkCount: Int = 0,
    val profileImageUrl: String? = null,
    val updatedAt: String? = null
)

data class InvestorInfo(
    val investorId: Int,
    val views: Int,
    val investorName: String,
    val contactEmail: String,
    val phoneNumber: String,
    val websiteUrl: String,
    val intro: String,
    val organizationName: String,
    val investorType: String,
    val preferredInvestmentSize: String,
    val preferredStages: List<String>,
    val preferredAreas: List<String>,
    val bookmarkCount: Int,
    val profileImageUrl: String?,
    val updatedAt: String

)