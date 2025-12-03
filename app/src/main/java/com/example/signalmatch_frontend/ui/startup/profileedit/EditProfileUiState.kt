package com.example.signalmatch_frontend.ui.startup.profileedit

import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm

sealed interface StartupEditProfileUiState {
    object Idle : StartupEditProfileUiState
    object Loading : StartupEditProfileUiState
    data class Loaded(val form: StartupProfileForm) : StartupEditProfileUiState
    data class Success(
        val message: String,
        val updatedAt: String
    ) : StartupEditProfileUiState
    data class Error(val message: String) : StartupEditProfileUiState
}