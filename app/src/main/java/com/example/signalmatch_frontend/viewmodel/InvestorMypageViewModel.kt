package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.ui.investor.mypage.InvestorMypageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestorMypageViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<InvestorMypageUiState>(InvestorMypageUiState.Loading)
    val uiState: StateFlow<InvestorMypageUiState> = _uiState

    fun loadInvestorProfile(userId: Int) {
        _uiState.value = InvestorMypageUiState.Loading

        viewModelScope.launch {
            try {
                val response = profileRepository.getInvestorProfile(userId)

                if (response.success && response.data != null) {
                    _uiState.value = InvestorMypageUiState.Success(response.data)
                } else {
                    _uiState.value = InvestorMypageUiState.Error(
                        response.message ?: "투자자 프로필 조회에 실패했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = InvestorMypageUiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}
