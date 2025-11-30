package com.example.signalmatch_frontend.ui.mypage.matching_list

import com.example.signalmatch_frontend.data.model.response.MatchData

data class MatchingListUiState(
    val isLoading: Boolean = false,
    val isCanceling: Boolean = false,
    val match: List<MatchData> = emptyList(),
    val errorMessage: String? = null
)
