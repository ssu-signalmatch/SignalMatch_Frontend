package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.InfoRepository
import com.example.signalmatch_frontend.data.repository.SearchRepository
import com.example.signalmatch_frontend.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor (
    private val searchRepository: SearchRepository,
    private val infoRepository: InfoRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    public fun loadSuggest () {
        _uiState.value = HomeUiState.Loading

        viewModelScope.launch {
            try {
                val response = searchRepository.search(
                    keyword = "",
                    areas = emptyList(),
                    page = 0,
                    size = 5
                )

                _uiState.value = HomeUiState.Success(
                    data = response
                )

            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(
                    e.message ?: "내부 서버 오류"
                )
            }
        }
    }

    fun loadInvestor (userId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = infoRepository.getInvestorInfo(userId)
                onResult(response.success)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun loadStartup (userId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = infoRepository.getStartupInfo(userId)
                onResult(response.success)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

}