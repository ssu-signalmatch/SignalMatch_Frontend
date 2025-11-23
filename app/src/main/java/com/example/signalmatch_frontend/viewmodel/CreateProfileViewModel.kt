package com.example.signalmatch_frontend.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.Request.InvestorCreateProfileRequest
import com.example.signalmatch_frontend.data.model.Request.StartupCreateProfileRequest
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import com.example.signalmatch_frontend.ui.investor.profilecreate.InvestorProfileForm
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val investorId: Long, val message: String?) : UiState
        data class Error(val message: String) : UiState
    }

    var uiState by mutableStateOf<UiState>(UiState.Idle)
        private set

    fun submit(form: InvestorProfileForm) {
        viewModelScope.launch {
            uiState = UiState.Loading

            runCatching {
                val req = InvestorCreateProfileRequest(
                    investorName = form.investorName,
                    phoneNumber = form.phoneNumber,
                    contactEmail = form.contactEmail,
                    organizationName = form.organizationName,
                    websiteUrl = form.websiteUrl,
                    intro = form.intro,
                    investorType = form.investorType,
                    preferredInvestmentSize = form.preferredInvestmentSize,
                    preferredStages = form.preferredStages.toList(),
                    preferredAreas = form.preferredAreas.toList()
                )
                profileRepository.investorCreateProfile(req)
            }.onSuccess { res ->
                if (res.success && res.data != null) {
                    uiState = UiState.Success(res.data.investorId, res.message)
                } else {
                    uiState = UiState.Error(res.message ?: "프로필 생성 실패")
                }
            }.onFailure { e ->
                uiState = UiState.Error(e.message ?: "네트워크 오류")
            }
        }
    }

    fun submit(form: StartupProfileForm) {
        viewModelScope.launch {
            uiState = UiState.Loading

            runCatching {
                val req = StartupCreateProfileRequest(
                    representativeBio = form.representativeBio, //임시
                    startupName = form.startupName,
                    status = form.status,
                    foundingDate = form.foundingDate,
                    address = form.address,
                    homepageUrl = form.homepageUrl,
                    contactEmail = form.contactEmail,
                    intro = form.intro,
                    representativeName = form.representativeName,
                    businessNumber = form.businessNumber,
                    employeeCount = form.employeeCount,
                    legalType = form.legalType,
                    scale = form.scale,
                    revenue = form.revenue,
                    profit = form.profit,
                    fundingRounds = form.fundingRounds,
                    totalFunding = form.totalFunding,
                    investorStages = form.investorStage,
                    businessAreas = form.businessAreas.toList()
                )
                profileRepository.startupCreateProfile(req)
            }.onSuccess { res ->
                if (res.success && res.data != null) {
                    uiState = UiState.Success(res.data.startupId, res.message)
                } else {
                    uiState = UiState.Error(res.message ?: "프로필 생성 실패")
                }
            }.onFailure { e ->
                uiState = UiState.Error(e.message ?: "네트워크 오류")
            }
        }
    }

}
