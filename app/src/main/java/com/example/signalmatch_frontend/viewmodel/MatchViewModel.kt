package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.request.MatchReasonCode
import com.example.signalmatch_frontend.data.repository.MatchRepository
import com.example.signalmatch_frontend.ui.mypage.matching_list.MatchingListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MatchingListUiState())
    val uiState: StateFlow<MatchingListUiState> = _uiState.asStateFlow()

    init {
        loadMatches()
    }

    fun loadMatches() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val response = repository.getMatch()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        match = response.data,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "매칭 목록을 불러오지 못했습니다."
                    )
                }
            }
        }
    }

    fun cancelMatch(
        matchId: Int,
        reasonCode: MatchReasonCode,
        reasonText: String?
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isCanceling = true, errorMessage = null) }
            try {
                repository.cancelMatch(matchId, reasonCode, reasonText)
                loadMatches()
                _uiState.update { it.copy(isCanceling = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isCanceling = false,
                        errorMessage = e.message ?: "매칭 취소에 실패했습니다."
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
