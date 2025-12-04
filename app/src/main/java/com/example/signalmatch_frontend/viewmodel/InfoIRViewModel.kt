package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.response.OtherDocumentData
import com.example.signalmatch_frontend.data.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class InfoIRViewModel @Inject constructor(
    private val documentRepository: DocumentRepository
) : ViewModel() {

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val documents: List<OtherDocumentData>) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun loadDocuments(userId: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val res = documentRepository.getOtherDocuments(userId)
                val documents = res.data ?: emptyList()

                if (!res.success || documents.isEmpty()) {
                    val msg = res.message?.takeIf { it.isNotBlank() }
                        ?: "IR 자료를 불러오지 못했습니다."
                    _uiState.value = UiState.Error(msg)
                    return@launch
                }

                _uiState.value = UiState.Success(documents)

            } catch (e: HttpException) {
                val msg = if (e.code() == 404) {
                    "등록된 IR 자료가 없습니다."
                } else {
                    "서버 오류가 발생했습니다. (${e.code()})"
                }
                _uiState.value = UiState.Error(msg)

            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value =
                    UiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}
