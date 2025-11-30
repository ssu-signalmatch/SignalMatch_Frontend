package com.example.signalmatch_frontend.ui.mypage.bookmark_list

import com.example.signalmatch_frontend.data.model.response.GetBookmarkData

data class GetBookmarkUiState(
    val isLoading: Boolean = false,
    val bookmark: List<GetBookmarkData> = emptyList(),
    val errorMessage: String? = null
)
