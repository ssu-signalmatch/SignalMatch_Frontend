package com.example.signalmatch_frontend.data.model.Request

data class StartupCreateProfileRequest(
    val representativeBio: String, //임시
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
    val businessAreas: List<String>
)