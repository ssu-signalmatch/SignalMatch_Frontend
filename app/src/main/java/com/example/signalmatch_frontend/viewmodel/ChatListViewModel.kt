
package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ChatRepository
import com.example.signalmatch_frontend.ui.chat.list.ChatListItem
import com.example.signalmatch_frontend.ui.chat.list.ChatListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel
@Inject constructor (
    private val chatRepository: ChatRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<ChatListUiState>(ChatListUiState.Loading)
    val uiState: StateFlow<ChatListUiState> = _uiState

    public fun loadChatRoomList () {
        _uiState.value = ChatListUiState.Loading

        viewModelScope.launch {
            try {
                val response = chatRepository.getChatRoomList()

                if (response != null) {
                    _uiState.value = ChatListUiState.Success(
                        data = ArrayList(response.map { res -> ChatListItem(res) })
                    )

                } else {
                    _uiState.value = ChatListUiState.Error(
                        "채팅방 목록 조회에 실패했어요."
                    )
                }

            } catch (e: Exception) {
                _uiState.value = ChatListUiState.Error(
                    e.message ?: "내부 서버 오류"
                )
            }
        }
    }


}
