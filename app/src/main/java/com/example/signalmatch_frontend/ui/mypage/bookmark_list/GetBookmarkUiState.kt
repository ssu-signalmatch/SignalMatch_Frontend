package com.example.signalmatch_frontend.ui.mypage.bookmark_list

import com.example.signalmatch_frontend.data.model.response.TargetItem

data class GetBookmarkUiState(
    val isLoading: Boolean = false,
    val bookmark: List<TargetItem> = emptyList(),
    val errorMessage: String? = null
)
