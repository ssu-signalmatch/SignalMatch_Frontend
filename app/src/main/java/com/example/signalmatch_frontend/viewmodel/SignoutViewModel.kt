// com/example/signalmatch/viewmodel/SignoutViewModel.kt
package com.example.signalmatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.signalmatch_frontend.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignoutViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var signoutMessage by mutableStateOf<String?>(null)
        private set

    var signoutSuccess by mutableStateOf<Boolean?>(null)
        private set

    fun signout() {
        viewModelScope.launch {
            try {
                val response = repository.signout()
                signoutMessage = response.message
                signoutSuccess = response.success
            } catch (e: retrofit2.HttpException) {
                signoutMessage = "회원 탈퇴 실패 (${e.code()})"
                signoutSuccess = false
            } catch (e: Exception) {
                signoutMessage = "네트워크 오류: ${e.message}"
                signoutSuccess = false
            }
        }
    }

    fun resetSignoutState() {
        signoutMessage = null
        signoutSuccess = null
    }
}
