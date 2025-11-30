package com.example.signalmatch_frontend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.local.TokenHolder
import com.example.signalmatch_frontend.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var loginSuccess by mutableStateOf<Boolean?>(null)
        private set

    var loginMessage by mutableStateOf<String?>(null)
        private set

    var userId: Int? = null
        private set

    var UserRole: String? = null
        private set

    var accessToken: String? = null
        private set

    fun login(id: String, password: String) {
        viewModelScope.launch {
            // 상태 초기화
            loginSuccess = null
            loginMessage = null

            try {
                val response = repository.login(id, password)

                if (response.success) {
                    val data = response.data

                    TokenHolder.accessToken = data.accessToken

                    userId = data.userId
                    UserRole = data.UserRole
                    accessToken = data.accessToken

                    loginSuccess = true
                } else {
                    loginSuccess = false
                    loginMessage = response.message
                }
            } catch (e: Exception) {
                loginSuccess = false
                loginMessage = e.message ?: "로그인 실패"
            }
        }
    }
}
