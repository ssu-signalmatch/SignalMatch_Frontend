package com.example.signalmatch_frontend.data.model.response

data class SearchResponse(
    val message: String,
    val data: SearchData,
    val success: Boolean
)

data class SearchData(
    val startups: List<StartupSearchDto>,
    val totalStartupCount: Int,
    val totalStartupPages: Int,
    val investors: List<InvestorSearchDto>,
    val totalInvestorCount: Int,
    val totalInvestorPages: Int
)

data class StartupSearchDto(
    val startupName: String,
    val intro: String,
    val legalType: String,
    val scale: String,
    val investorStages: String,
    val businessAreas: List<String>
)

data class InvestorSearchDto(
    val investorName: String,
    val intro: String,
    val organizationName: String,
    val investorType: String,
    val preferredAreas: List<String>
)
