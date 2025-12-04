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

                if (response.success && response.data != null) {
                    val p = response.data
                    val form = StartupProfileForm(
                        startupName = p.startupName,
                        status = p.status,
                        foundingDate = p.foundingDate,
                        address = p.address,
                        homepageUrl = p.homepageUrl,
                        contactEmail = p.contactEmail,
                        intro = p.intro,
                        representativeName = p.representativeName,
                        businessNumber = p.businessNumber,
                        employeeCount = p.employeeCount,
                        legalType = p.legalType,
                        scale = p.scale,
                        revenue = p.revenue,
                        profit = p.profit,
                        fundingRounds = p.fundingRounds,
                        totalFunding = p.totalFunding,
                        investorStage = p.investorStages,
                        businessAreas = p.businessAreas.toMutableSet(),
                        history = p.history
                    )
                    _uiState.value = StartupEditProfileUiState.Loaded(form)
                } else {
                    _uiState.value = StartupEditProfileUiState.Error(
                        response.message ?: "스타트업 프로필을 불러오지 못했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = StartupEditProfileUiState.Error(
                    e.message ?: "알 수 없는 오류가 발생했어요."
                )
            }
        }
    }

    fun editProfile(req: StartupEditProfileRequest) {
        viewModelScope.launch {
            _uiState.value = StartupEditProfileUiState.Loading
            try {
                val response = profileRepository.editStartupProfile(req)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.success) {
                        val updatedAt = body.data.updatedAt.take(10)
                        _uiState.value = StartupEditProfileUiState.Success(
                            message = body.message ?: "프로필이 수정되었어요.",
                            updatedAt = updatedAt
                        )
                    } else {
                        _uiState.value = StartupEditProfileUiState.Error(
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
                    _uiState.value = StartupEditProfileUiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _uiState.value = StartupEditProfileUiState.Error(
                    e.message ?: "프로필 수정 중 알 수 없는 오류가 발생했어요."
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = StartupEditProfileUiState.Idle
    }
}

@HiltViewModel
class InvestorEditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<InvestorEditProfileUiState>(InvestorEditProfileUiState.Idle)
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
                _uiState.value =
                    InvestorEditProfileUiState.Error("프로필 정보를 불러오지 못했습니다.")
            }
        }
    }

    fun editProfile(request: InvestorEditProfileRequest) {
        viewModelScope.launch {
            _uiState.value = InvestorEditProfileUiState.Loading

            try {
                val response = profileRepository.editInvestorProfile(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.success) {
                        val updatedAt = body.data.updatedAt.take(10)
                        val msg = body.message ?: "수정되었습니다."
                        _uiState.value = InvestorEditProfileUiState.Success(
                            message = msg,
                            updatedAt = updatedAt
                        )
                    } else {
                        _uiState.value = InvestorEditProfileUiState.Error(
                            body?.message ?: "프로필 수정 실패"
                        )
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = try {
                        JSONObject(errorBody ?: "").optString("message")
                    } catch (e: Exception) {
                        "프로필 수정 실패"
                    }
                    _uiState.value = InvestorEditProfileUiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _uiState.value = InvestorEditProfileUiState.Error(
                    e.message ?: "프로필 수정 실패"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = InvestorEditProfileUiState.Idle
    }
}