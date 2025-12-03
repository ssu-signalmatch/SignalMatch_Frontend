package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.request.InvestorEditProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupEditProfileRequest
import com.example.signalmatch_frontend.data.model.response.InvestorProfileData
import com.example.signalmatch_frontend.data.model.response.StartupProfileData
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.data.repository.UserRepository
import com.example.signalmatch_frontend.ui.investor.profileedit.InvestorProfileForm
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class InvestorProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
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

    private val _lastUpdatedDate = MutableStateFlow<String?>(null)
    val lastUpdatedDate: StateFlow<String?> = _lastUpdatedDate

    private val userId: Int = savedStateHandle["userId"] ?: -1

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
                val profileResponse = profileRepository.getInvestorProfile(userId)
                if (!(profileResponse.success && profileResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        profileResponse.message ?: "투자자 프로필 상세조회에 실패했어요."
                    )
                    return@launch
                }

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
                    val body = response.body()

                    if (body != null && body.success) {
                        _lastUpdatedDate.value = body.data.updatedAt.take(10)
                        loadInvestorProfileDetail()
                    } else {
                        _uiState.value = UiState.Error(
                            body?.message ?: "프로필 수정에 실패했어요."
                        )
                    }
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

@HiltViewModel
class StartupProfileDetailViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
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

    private val _lastUpdatedDate = MutableStateFlow<String?>(null)
    val lastUpdatedDate: StateFlow<String?> = _lastUpdatedDate

    private val userId: Int = savedStateHandle["userId"] ?: -1

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
                val profileResponse = profileRepository.getStartupProfile(userId)
                if (!(profileResponse.success && profileResponse.data != null)) {
                    _uiState.value = UiState.Error(
                        profileResponse.message ?: "스타트업 프로필 상세조회에 실패했어요."
                    )
                    return@launch
                }

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

    fun updateProfile(
        form: StartupProfileForm,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                val request = StartupEditProfileRequest(
                    startupName = form.startupName,
                    foundingDate = form.foundingDate,
                    scale = form.scale,
                    businessNumber = form.businessNumber,
                    address = form.address,
                    homepageUrl = form.homepageUrl,
                    contactEmail = form.contactEmail,
                    intro = form.intro,
                    representativeName = form.representativeName,
                    employeeCount = form.employeeCount,
                    legalType = form.legalType,
                    status = form.status,
                    revenue = form.revenue.toInt(),
                    profit = form.profit.toInt(),
                    fundingRounds = form.fundingRounds,
                    totalFunding = form.totalFunding.toInt(),
                    history = form.history,
                    investorStages = form.investorStage,
                    businessAreas = form.businessAreas.toList()
                )

                val response = profileRepository.editStartupProfile(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.success) {
                        val updatedAt = body.data.updatedAt.take(10)
                        onSuccess(updatedAt)
                    } else {
                        onError(body?.message ?: "프로필 수정에 실패했어요.")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = try {
                        JSONObject(errorBody ?: "").optString("message")
                    } catch (e: Exception) {
                        "프로필 수정에 실패했어요."
                    }
                    onError(errorMsg)
                }
            } catch (e: Exception) {
                onError(e.message ?: "프로필 수정 중 알 수 없는 오류가 발생했어요.")
            }
        }
    }
}
