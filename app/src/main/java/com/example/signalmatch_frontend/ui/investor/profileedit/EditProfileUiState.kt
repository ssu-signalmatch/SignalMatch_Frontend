package com.example.signalmatch_frontend.ui.investor.profileedit

sealed interface InvestorEditProfileUiState {
    object Idle : InvestorEditProfileUiState
    object Loading : InvestorEditProfileUiState
    data class Loaded(val form: InvestorProfileForm) : InvestorEditProfileUiState
    data class Success(val message: String) : InvestorEditProfileUiState
    data class Error(val message: String) : InvestorEditProfileUiState
}
