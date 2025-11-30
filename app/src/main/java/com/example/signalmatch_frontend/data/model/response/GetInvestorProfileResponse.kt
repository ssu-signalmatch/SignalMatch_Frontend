package com.example.signalmatch_frontend.data.model.response

data class GetInvestorProfileResponse(
    val message: String?,
    val data: InvestorProfileData?,
    val success: Boolean
)

data class InvestorProfileData(
    val investorId: Long,
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
    val preferredAreas: List<String>
)
