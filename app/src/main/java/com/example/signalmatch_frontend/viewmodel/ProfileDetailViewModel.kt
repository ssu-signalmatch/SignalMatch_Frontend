package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.local.PreferenceDataStore
import com.example.signalmatch_frontend.data.model.request.InvestorEditProfileRequest
import com.example.signalmatch_frontend.data.model.response.InvestorProfileData
import com.example.signalmatch_frontend.data.model.response.StartupProfileData
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.data.repository.UserRepository
import com.example.signalmatch_frontend.ui.investor.profileedit.InvestorProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.LocalDate
import javax.inject.Inject

// ====================== 투자자 프로필 상세 ======================

@HiltViewModel
class InvestorProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val preferenceDataStore: PreferenceDataStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(
            val profile: InvestorProfileData,
            val profileImageUrl: String?,
            val bookmarkCount: Int
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val userId: Int = savedStateHandle["userId"] ?: -1

    val lastUpdatedDate = preferenceDataStore.lastUpdatedDate

    init {
        if (userId != -1) {
            loadInvestorProfileDetail()
        } else {
            _uiState.value = UiState.Error("잘못된 사용자 정보입니다.")
        }
    }

    fun refresh() {
        if (userId != -1) {
            loadInvestorProfileDetail()
        }
    }

    private fun loadInvestorProfileDetail() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // 1) 투자자 프로필 조회
                val profileResponse = profileRepository.getInvestorProfile(userId)
                if (!(profileResponse.success && profileResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        profileResponse.message ?: "투자자 프로필 상세조회에 실패했어요."
                    )
                    return@launch
                }

                // 2) /api/me 조회 (마이페이지 정보)
                val meResponse = userRepository.getMe()
                if (!(meResponse.success && meResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        meResponse.message ?: "마이페이지 정보 조회에 실패했어요."
                    )
                    return@launch
                }

                val imageUrl = meResponse.data.profileImageUrl
                val bookmarkCount = meResponse.data.bookmarkCount

                _uiState.value = UiState.Success(
                    profile = profileResponse.data,
                    profileImageUrl = imageUrl,
                    bookmarkCount = bookmarkCount
                )

            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }

    fun updateProfile(form: InvestorProfileForm) {
        viewModelScope.launch {
            try {
                val request = InvestorEditProfileRequest(
                    investorName = form.investorName,
                    contactEmail = form.contactEmail,
                    phoneNumber = form.phoneNumber,
                    organizationName = form.organizationName,
                    websiteUrl = form.websiteUrl,
                    intro = form.intro,
                    investorType = form.investorType,
                    preferredInvestmentSize = form.preferredInvestmentSize,
                    preferredStages = form.preferredStages.toList(),
                    preferredAreas = form.preferredAreas.toList()
                )

                val response = profileRepository.editInvestorProfile(request)

                if (response.isSuccessful) {
                    val today = LocalDate.now().toString()
                    preferenceDataStore.saveLastUpdatedDate(today)

                    loadInvestorProfileDetail()

                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = try {
                        JSONObject(errorBody ?: "").optString("message")
                    } catch (e: Exception) {
                        "프로필 수정에 실패했어요."
                    }
                    _uiState.value = UiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "프로필 수정 중 알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}


// ====================== 스타트업 프로필 상세 ======================

@HiltViewModel
class StartupProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val preferenceDataStore: PreferenceDataStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(
            val profile: StartupProfileData,
            val profileImageUrl: String?,
            val bookmarkCount: Int
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val userId: Int = savedStateHandle["userId"] ?: -1

    val lastUpdatedDate = preferenceDataStore.lastUpdatedDate

    init {
        if (userId != -1) {
            loadStartupProfileDetail()
        } else {
            _uiState.value = UiState.Error("잘못된 사용자 정보입니다.")
        }
    }

    fun refresh() {
        if (userId != -1) {
            loadStartupProfileDetail()
        }
    }

    private fun loadStartupProfileDetail() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // 1) 스타트업 프로필 조회
                val profileResponse = profileRepository.getStartupProfile(userId)
                if (!(profileResponse.success && profileResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        profileResponse.message ?: "스타트업 프로필 상세조회에 실패했어요."
                    )
                    return@launch
                }

                // 2) /api/me 조회
                val meResponse = userRepository.getMe()
                if (!(meResponse.success && meResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        meResponse.message ?: "마이페이지 정보 조회에 실패했어요."
                    )
                    return@launch
                }

                val imageUrl = meResponse.data.profileImageUrl
                val bookmarkCount = meResponse.data.bookmarkCount

                _uiState.value = UiState.Success(
                    profile = profileResponse.data,
                    profileImageUrl = imageUrl,
                    bookmarkCount = bookmarkCount
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }
}
