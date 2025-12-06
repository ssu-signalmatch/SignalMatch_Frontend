package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.response.InvestorInfo
import com.example.signalmatch_frontend.data.model.response.StartupInfo
import com.example.signalmatch_frontend.data.repository.InfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.HttpException

@HiltViewModel
class StartupInfoViewModel @Inject constructor(
    private val infoRepository: InfoRepository
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(
            val startup: StartupInfo,
            val profileImageUrl: String?,
            val updatedAt: String?,
            val bookmarkCount: Int,
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _lastUpdatedDate = MutableStateFlow<String?>(null)
    val lastUpdatedDate: StateFlow<String?> = _lastUpdatedDate

    fun loadInfo(userId: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val res = infoRepository.getStartupInfo(userId)
                val data = res.data
                val startup = data.profile

                data.updatedAt?.let {
                    _lastUpdatedDate.value = it.take(10)
                }

                if (!res.success || startup == null) {
                    val msg = if (res.message.isNullOrBlank()) {
                        "사용자의 스타트업 정보를 찾을 수 없습니다."
                    } else res.message
                    _uiState.value = UiState.Error(msg)
                    return@launch
                }

                _uiState.value = UiState.Success(
                    startup = startup,
                    profileImageUrl = data.profileImageUrl,
                    updatedAt = data.updatedAt
                        ?.take(10)
                        ?.replace("-", "."),
                    bookmarkCount = data.bookmarkCount
                )

            } catch (e: HttpException) {
                if (e.code() == 404)
                    _uiState.value = UiState.Error("사용자의 스타트업 정보를 찾을 수 없습니다.")
                else
                    _uiState.value = UiState.Error("서버 오류가 발생했습니다. (${e.code()})")
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = UiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }
}

@HiltViewModel
class InvestorInfoViewModel @Inject constructor(
    private val infoRepository: InfoRepository
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(
            val investor: InvestorInfo,
            val profileImageUrl: String?,
            val updatedAt: String?,
            val bookmarkCount: Int,
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _lastUpdatedDate = MutableStateFlow<String?>(null)
    val lastUpdatedDate: StateFlow<String?> = _lastUpdatedDate

    fun loadInfo(userId: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val res = infoRepository.getInvestorInfo(userId)
                val data = res.data
                val investor = data.profile

                data.updatedAt?.let {
                    _lastUpdatedDate.value = it.take(10)
                }

                if (!res.success || investor == null) {
                    val msg = if (res.message.isNullOrBlank()) {
                        "사용자의 스타트업 정보를 찾을 수 없습니다."
                    } else res.message
                    _uiState.value = UiState.Error(msg)
                    return@launch
                }

                _uiState.value = UiState.Success(
                    investor = investor,
                    profileImageUrl = data.profileImageUrl,
                    updatedAt = data.updatedAt
                        ?.take(10)
                        ?.replace("-", "."),
                    bookmarkCount = data.bookmarkCount
                )

            } catch (e: HttpException) {
                if (e.code() == 404)
                    _uiState.value = UiState.Error("사용자의 스타트업 정보를 찾을 수 없습니다.")
                else
                    _uiState.value = UiState.Error("서버 오류가 발생했습니다. (${e.code()})")
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = UiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }
}
