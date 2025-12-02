package com.example.signalmatch_frontend.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileImageUiState(
    val isLoading: Boolean = false,
    val imageUrl: String? = null,
    val error: String? = null
)

@HiltViewModel
class ProfileImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileImageUiState())
    val uiState: StateFlow<ProfileImageUiState> = _uiState

    fun setInitialImageUrl(url: String?) {
        if (!url.isNullOrBlank() && _uiState.value.imageUrl == null) {
            _uiState.update { it.copy(imageUrl = url) }
        }
    }

    fun uploadProfileImage(userId: Int, imageUri: Uri) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            runCatching {
                imageRepository.uploadProfileImage(userId, imageUri)
            }.onSuccess { publicUrl ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        imageUrl = publicUrl,
                        error = null
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "이미지 업로드/저장 중 오류가 발생했습니다."
                    )
                }
            }
        }
    }
}
