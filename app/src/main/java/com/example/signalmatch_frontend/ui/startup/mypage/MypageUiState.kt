package com.example.signalmatch_frontend.ui.startup.mypage

import com.example.signalmatch_frontend.data.model.response.StartupProfileData

sealed class StartupMypageUiState {
    data object Loading : StartupMypageUiState()
    data class Success(
        val data: StartupProfileData
    ) : StartupMypageUiState()
    data class Error(
        val message: String
    ) : StartupMypageUiState()
}