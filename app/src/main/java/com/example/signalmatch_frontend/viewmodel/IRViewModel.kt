package com.example.signalmatch_frontend.viewmodel

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.model.response.DocumentListItem
import com.example.signalmatch_frontend.data.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

sealed interface IRUiState {
    object Idle : IRUiState
    object Loading : IRUiState
    object Success : IRUiState
    data class Error(val message: String) : IRUiState
}

sealed interface DocumentUiState {
    object Loading : DocumentUiState
    data class Success(val documents: List<DocumentListItem>) : DocumentUiState
    data class Error(val message: String) : DocumentUiState
}

@HiltViewModel
class IRViewModel @Inject constructor(
    private val irRepository: DocumentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<IRUiState>(IRUiState.Idle)
    val uiState: StateFlow<IRUiState> = _uiState

    private val _documentUiState =
        MutableStateFlow<DocumentUiState>(DocumentUiState.Loading)
    val documentUiState: StateFlow<DocumentUiState> = _documentUiState

    fun uploadIr(
        contentResolver: ContentResolver,
        uri: Uri,
        startupId: Int
    ) {
        _uiState.value = IRUiState.Loading

        viewModelScope.launch {
            val result = irRepository.uploadIrDocument(contentResolver, uri, startupId)

            _uiState.value = result.fold(
                onSuccess = { IRUiState.Success },
                onFailure = { e ->
                    if (e is HttpException) {
                        val code = e.code()
                        val errorBody = e.response()?.errorBody()?.string()
                        Log.e("IR_UPLOAD", "HTTP $code errorBody = $errorBody")
                    } else {
                        Log.e("IR_UPLOAD", "other error: ${e.message}", e)
                    }
                    IRUiState.Error(e.message ?: "IR 업로드 실패")
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = IRUiState.Idle
    }


    fun loadDocuments() {
        _documentUiState.value = DocumentUiState.Loading

        viewModelScope.launch {
            irRepository.getDocuments()
                .onSuccess { docs ->
                    _documentUiState.value = DocumentUiState.Success(docs)
                }
                .onFailure { e ->
                    _documentUiState.value =
                        DocumentUiState.Error(e.message ?: "문서 조회 실패")
                }
        }
    }


    fun deleteDocument(documentId: Int) {
        viewModelScope.launch {
            irRepository.deleteDocument(documentId)
                .onSuccess {
                    loadDocuments()
                }
                .onFailure { e ->
                    Log.e("DOCUMENT_DELETE", "delete error: ${e.message}", e)
                }
        }
    }
}
