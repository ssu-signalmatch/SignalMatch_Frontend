package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.ui.startup.mypage.StartupMypageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupMypageViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<StartupMypageUiState>(StartupMypageUiState.Loading)
    val uiState: StateFlow<StartupMypageUiState> = _uiState

    fun loadStartupProfile(userId: Int) {
        _uiState.value = StartupMypageUiState.Loading

        viewModelScope.launch {
            try {
                val response = profileRepository.getStartupProfile(userId)

                if (response.success && response.data != null) {
                    _uiState.value = StartupMypageUiState.Success(response.data)
                } else {
                    _uiState.value = StartupMypageUiState.Error(
                        response.message ?: "스타트업 프로필 조회에 실패했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = StartupMypageUiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}
