package com.example.signalmatch_frontend.data.model.request

data class StartupEditProfileRequest(
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
    val revenue: Int,
    val profit: Int,
    val fundingRounds: Int,
    val totalFunding: Int,
    val investorStages: String,
    val businessAreas: List<String>,
    val history: String
)
