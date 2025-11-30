package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PostLoginViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        object GoHome : UiState
        object GoInvestorCreateProfile : UiState
        object GoStartupCreateProfile : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun decideNext(userId: Int, userRole: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                when (userRole.uppercase()) {
                    "INVESTOR" -> handleInvestor(userId)
                    "STARTUP"  -> handleStartup(userId)
                    else -> _uiState.value = UiState.Error("알 수 없는 역할: $userRole")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    private suspend fun handleInvestor(userId: Int) {
        try {
            profileRepository.getInvestorProfile(userId)
            _uiState.value = UiState.GoHome
        } catch (e: HttpException) {
            if (e.code() == 404) {
                _uiState.value = UiState.GoInvestorCreateProfile
            } else {
                _uiState.value = UiState.Error("투자자 프로필 조회 실패 (${e.code()})")
            }
        }
    }

    private suspend fun handleStartup(userId: Int) {
        try {
            profileRepository.getStartupProfile(userId)
            _uiState.value = UiState.GoHome
        } catch (e: HttpException) {
            if (e.code() == 404) {
                _uiState.value = UiState.GoStartupCreateProfile
            } else {
                _uiState.value = UiState.Error("스타트업 프로필 조회 실패 (${e.code()})")
            }
        }
    }
}
