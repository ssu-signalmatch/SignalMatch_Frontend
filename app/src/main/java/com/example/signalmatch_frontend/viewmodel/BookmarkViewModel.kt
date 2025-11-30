package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.BookmarkRepository
import com.example.signalmatch_frontend.ui.mypage.bookmark_list.GetBookmarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: BookmarkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GetBookmarkUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBookmark()
    }

    fun loadBookmark() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val response = repository.getBookmark()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        bookmark = response.data,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "오류가 발생했습니다."
                    )
                }
            }
        }
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            repository.deleteBookmark(id)
            loadBookmark()
        }
    }

}
