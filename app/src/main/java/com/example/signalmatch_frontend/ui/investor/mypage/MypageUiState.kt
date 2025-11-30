package com.example.signalmatch_frontend.ui.investor.mypage

import com.example.signalmatch_frontend.data.model.response.InvestorProfileData

sealed class InvestorMypageUiState {
    data object Loading : InvestorMypageUiState()
    data class Success(
        val data: InvestorProfileData
    ) : InvestorMypageUiState()
    data class Error(
        val message: String
    ) : InvestorMypageUiState()
}
