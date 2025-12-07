package com.example.signalmatch_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.repository.ChatRepository
import com.example.signalmatch_frontend.ui.chat.room.ChatRoomUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel
@Inject constructor (
    private val chatRepository: ChatRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<ChatRoomUiState>(ChatRoomUiState.Loading)
    val uiState: StateFlow<ChatRoomUiState> = _uiState

    fun initChatMessages (
        roomId: Long
    ) {
        return this.loadChatMessages(roomId = roomId)
    }

    fun loadChatMessages (
        roomId: Long,
        lastMessageId: Long? = null,
        size: Int? = 50
    ) {
        viewModelScope.launch {
            _uiState.value = ChatRoomUiState.Loading
            try {
                val response = chatRepository.getMessages(
                    roomId = roomId,
                    lastMessageId = lastMessageId,
                    size = size
                )
                if (response != null) {


                    _uiState.value = ChatRoomUiState.Success(
                        ArrayList()
                    )

                } else {
                    _uiState.value = ChatRoomUiState.Error(
                        "메시지 조회에 실패했어요."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = ChatRoomUiState.Error(
                    e.message ?: "내부 서버 오류"
                )
            }
        }
    }

    fun sendMessage (

    ) {

    }

}