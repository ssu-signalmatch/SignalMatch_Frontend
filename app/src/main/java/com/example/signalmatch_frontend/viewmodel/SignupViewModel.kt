package com.example.signalmatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.example.signalmatch_frontend.data.repository.AuthRepository

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var signupMessage by mutableStateOf<String?>(null)
        private set

    var signupSuccess by mutableStateOf<Boolean?>(null)
        private set

    fun signup(loginId: String, password: String, name: String, userRole: String) {
        viewModelScope.launch {
            try {
                val response = repository.signup(loginId, password, name, userRole)
                signupMessage = response.message
                signupSuccess = response.success
            } catch (e: retrofit2.HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                if (errorBody?.contains("이미 존재하는 아이디") == true) {
                    signupMessage = "이미 존재하는 아이디입니다. 다시 입력해주세요."
                } else {
                    signupMessage = "회원가입 실패"
                }
                signupSuccess = false
            } catch (e: Exception) {
                signupMessage = "네트워크 오류: ${e.message}"
                signupSuccess = false
            }
        }
    }
}
