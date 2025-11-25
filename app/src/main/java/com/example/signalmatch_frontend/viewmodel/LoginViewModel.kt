package com.example.signalmatch_frontend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.local.UserPreference
import com.example.signalmatch_frontend.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    var loginSuccess by mutableStateOf<Boolean?>(null)
        private set

    var profileCompleted by mutableStateOf(false)
        private set

    var userRole by mutableStateOf<String?>(null)
        private set

    fun login(id: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(id, password)

                if (response.success) {
                    val token = response.data.accessToken

                    userPreference.saveAccessToken(token)

                    userRole = userPreference.userRoleFlow.first()

                    profileCompleted = userPreference.profileCompletedFlow.first()

                    loginSuccess = true
                } else {
                    loginSuccess = false
                }
            } catch (e: Exception) {
                loginSuccess = false
            }
        }
    }
}
