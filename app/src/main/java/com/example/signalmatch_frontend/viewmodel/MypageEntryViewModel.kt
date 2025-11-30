package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.signalmatch_frontend.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MypageEntryViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    fun openMypage(
        navController: NavController,
        userId: Int,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                profileRepository.getInvestorProfile(userId)
                navController.navigate("investor-mypage/$userId") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                return@launch
            } catch (e: HttpException) {
                if (e.code() != 404) {
                    onError("투자자 프로필 조회 실패 (${e.code()})")
                    return@launch
                }

            } catch (e: Exception) {
                onError(e.message ?: "투자자 프로필 조회 중 오류 발생")
                return@launch
            }

            try {
                profileRepository.getStartupProfile(userId)
                navController.navigate("startup-mypage/$userId") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    onError("투자자/스타트업 프로필이 모두 없습니다.")
                } else {
                    onError("스타트업 프로필 조회 실패 (${e.code()})")
                }
            } catch (e: Exception) {
                onError(e.message ?: "스타트업 프로필 조회 중 오류 발생")
            }
        }
    }
}
