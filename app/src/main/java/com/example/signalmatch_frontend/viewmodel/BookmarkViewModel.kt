package com.example.signalmatch_frontend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.BookmarkRepository
import com.example.signalmatch_frontend.ui.mypage.bookmark_list.GetBookmarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

private const val TAG = "BookmarkViewModel"

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GetBookmarkUiState())
    val uiState: StateFlow<GetBookmarkUiState> = _uiState.asStateFlow()

    // 조회
    fun loadBookmarks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            bookmarkRepository.getBookmarks()
                .onSuccess { list ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        bookmark = list,
                        errorMessage = null
                    )
                }
                .onFailure { e ->
                    Log.e(TAG, "loadBookmarks failed", e)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "북마크 조회에 실패했습니다."
                    )
                }
        }
    }

    // 추가
    fun addBookmark(
        targetUserId: Int,
        onComplete: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            bookmarkRepository.addBookmark(targetUserId)
                .onSuccess { data ->
                    Log.d(TAG, "addBookmark success: $data")
                    loadBookmarks()
                    onComplete()
                }
                .onFailure { e ->
                    if (e is HttpException && e.code() == 409) {
                        Log.w(TAG, "Bookmark already exists (409). Treat as success.")
                        loadBookmarks()
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                        onComplete()
                        return@onFailure
                    }

                    Log.e(TAG, "addBookmark failed", e)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "북마크 추가에 실패했습니다."
                    )
                }
        }
    }

    // 삭제
    fun deleteBookmark(
        targetUserId: Int,
        onComplete: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            bookmarkRepository.deleteBookmark(targetUserId)
                .onSuccess {
                    Log.d(TAG, "deleteBookmark success: targetUserId=$targetUserId")
                    loadBookmarks()
                    onComplete()
                }
                .onFailure { e ->
                    Log.e(TAG, "deleteBookmark failed", e)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "북마크 해제에 실패했습니다."
                    )
                }
        }
    }

}
