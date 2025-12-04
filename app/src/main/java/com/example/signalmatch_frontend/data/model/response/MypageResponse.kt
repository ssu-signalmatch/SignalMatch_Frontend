package com.example.signalmatch_frontend.data.model.response

data class MyPageResponse(
    val message: String,
    val data: MeData,
    val success: Boolean
)

data class MeData(
    val mypage: Mypage,
    val bookmarkCount: Int,
    val profileImageUrl: String?,
    val updatedAt: String?
)

sealed interface Mypage

data class InvestorMypageData(
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
    val preferredAreas: List<String>
) : Mypage

data class StartupMypageData(
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
) : Mypage
