package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.request.InvestorEditProfileRequest
import com.example.signalmatch_frontend.data.model.request.StartupEditProfileRequest
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.ui.investor.profileedit.InvestorEditProfileUiState
import com.example.signalmatch_frontend.ui.investor.profileedit.InvestorProfileForm
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm
import com.example.signalmatch_frontend.ui.startup.profileedit.StartupEditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

@HiltViewModel
class StartupEditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<StartupEditProfileUiState>(StartupEditProfileUiState.Idle)
    val uiState: StateFlow<StartupEditProfileUiState> = _uiState

    fun loadProfile(userId: Int) {
        viewModelScope.launch {
            _uiState.value = StartupEditProfileUiState.Loading
            try {
                val response = profileRepository.getStartupProfile(userId)
                val profile = response.data

                if (profile != null) {
                    val form = StartupProfileForm(
                        history = profile.history,
                        startupName = profile.startupName,
                        foundingDate = profile.foundingDate,
                        scale = profile.scale,
                        businessNumber = profile.businessNumber,
                        representativeName = profile.representativeName,
                        address = profile.address,
                        homepageUrl = profile.homepageUrl,
                        contactEmail = profile.contactEmail,
                        intro = profile.intro,
                        status = profile.status,
                        businessAreas = profile.businessAreas.toSet(),
                        legalType = profile.legalType,
                        employeeCount = profile.employeeCount,
                        totalFunding = profile.totalFunding,
                        fundingRounds = profile.fundingRounds,
                        revenue = profile.revenue,
                        profit = profile.profit,
                        investorStage = profile.investorStages
                    )
                    _uiState.value = StartupEditProfileUiState.Loaded(form)
                } else {
                    _uiState.value =
                        StartupEditProfileUiState.Error("스타트업 프로필 정보를 불러오지 못했습니다.")
                }
            } catch (e: Exception) {
                _uiState.value =
                    StartupEditProfileUiState.Error("서버 통신 중 오류가 발생했습니다.")
            }
        }
    }

    fun editProfile(request: StartupEditProfileRequest) {
        viewModelScope.launch {
            _uiState.value = StartupEditProfileUiState.Loading
            try {
                val response = profileRepository.editStartupProfile(request)
                if (response.isSuccessful) {
                    val msg = response.body()?.message ?: "수정되었습니다."
                    _uiState.value = StartupEditProfileUiState.Success(msg)
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = try {
                        JSONObject(errorBody ?: "").optString("message")
                    } catch (e: Exception) {
                        "프로필 수정 실패"
                    }
                    _uiState.value = StartupEditProfileUiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _uiState.value =
                    StartupEditProfileUiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    fun resetState() {
        _uiState.value = StartupEditProfileUiState.Idle
    }
}

@HiltViewModel
class InvestorEditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<InvestorEditProfileUiState>(InvestorEditProfileUiState.Idle)
    val uiState: StateFlow<InvestorEditProfileUiState> = _uiState

    fun loadProfile(userId: Int) {
        viewModelScope.launch {
            _uiState.value = InvestorEditProfileUiState.Loading
            try {
                val profile = profileRepository.getInvestorProfile(userId).data
                val form = InvestorProfileForm(
                    investorName = profile?.investorName ?: "",
                    contactEmail = profile?.contactEmail ?: "",
                    phoneNumber = profile?.phoneNumber ?: "",
                    organizationName = profile?.organizationName ?: "",
                    websiteUrl = profile?.websiteUrl ?: "",
                    intro = profile?.intro ?: "",
                    investorType = profile?.investorType ?: "",
                    preferredInvestmentSize = profile?.preferredInvestmentSize ?: "",
                    preferredStages = profile?.preferredStages?.toSet() ?: emptySet(),
                    preferredAreas = profile?.preferredAreas?.toSet() ?: emptySet()
                )

                _uiState.value = InvestorEditProfileUiState.Loaded(form)
            } catch (e: Exception) {
                _uiState.value = InvestorEditProfileUiState.Error("프로필 정보를 불러오지 못했습니다.")
            }
        }
    }

    fun editProfile(request: InvestorEditProfileRequest) {
        viewModelScope.launch {
            _uiState.value = InvestorEditProfileUiState.Loading

            val response = profileRepository.editInvestorProfile(request)
            if (response.isSuccessful) {
                val msg = response.body()?.message ?: "수정되었습니다."
                _uiState.value = InvestorEditProfileUiState.Success(msg)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = try {
                    JSONObject(errorBody ?: "").optString("message")
                } catch (e: Exception) {
                    "프로필 수정 실패"
                }
                _uiState.value = InvestorEditProfileUiState.Error(errorMsg)
            }
        }
    }

    fun resetState() {
        _uiState.value = InvestorEditProfileUiState.Idle
    }
}