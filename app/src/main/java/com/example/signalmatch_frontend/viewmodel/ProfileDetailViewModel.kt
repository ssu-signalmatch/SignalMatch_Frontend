package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.response.GetInvestorProfileResponse
import com.example.signalmatch_frontend.data.model.response.InvestorProfileData
import com.example.signalmatch_frontend.data.model.response.StartupProfileData
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestorProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(val data: InvestorProfileData) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val userId: Int = savedStateHandle["userId"] ?: -1

    init {
        if (userId != -1) {
            loadInvestorProfileDetail(userId)
        } else {
            _uiState.value = UiState.Error("잘못된 사용자 정보입니다.")
        }
    }

    private fun loadInvestorProfileDetail(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = profileRepository.getInvestorProfile(userId)
                if (response.success && response.data != null) {
                    _uiState.value = UiState.Success(response.data)
                } else {
                    _uiState.value = UiState.Error(
                        response.message ?: "투자자 프로필 상세조회에 실패했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}

@HiltViewModel
class StartupProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(val data: StartupProfileData) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val userId: Int = savedStateHandle["userId"] ?: -1

    init {
        if (userId != -1) {
            loadStartupProfileDetail(userId)
        } else {
            _uiState.value = UiState.Error("잘못된 사용자 정보입니다.")
        }
    }

    private fun loadStartupProfileDetail(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = profileRepository.getStartupProfile(userId)
                if (response.success && response.data != null) {
                    _uiState.value = UiState.Success(response.data)
                } else {
                    _uiState.value = UiState.Error(
                        response.message ?: "투자자 프로필 상세조회에 실패했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}