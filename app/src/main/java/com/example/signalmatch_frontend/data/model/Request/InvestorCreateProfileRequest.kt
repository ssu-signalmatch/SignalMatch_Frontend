package com.example.signalmatch_frontend.data.model.Request

data class InvestorCreateProfileRequest(
    val investorName: String?,
    val phoneNumber: String,
    val contactEmail: String,
    val organization: String,
    val websiteUrl: String,
    val intro: String,
    val investorType: String?,
    val preferredInvestmentSize: String?,
    val preferredStages: List<String>,
    val preferredAreas: List<String>
)
