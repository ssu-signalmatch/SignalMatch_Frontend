package com.example.signalmatch_frontend.ui.home

import com.example.signalmatch_frontend.data.model.response.SearchResponse

sealed class HomeUiState {

    data object Loading: HomeUiState()

    data class Success (
        val data: SearchResponse?
    ): HomeUiState()

    data class Error (
        val message: String
    ): HomeUiState()

}