package com.example.signalmatch_frontend.data.model.request

data class InvestorEditProfileRequest(
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
