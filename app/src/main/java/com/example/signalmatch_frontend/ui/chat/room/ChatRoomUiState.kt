package com.example.signalmatch_frontend.ui.chat.room

sealed class ChatRoomUiState {

    data object Loading: ChatRoomUiState()

    data class Success (
        val data: ArrayList<ChatItem>?
    ): ChatRoomUiState()

    data class Error (
        val message: String
    ): ChatRoomUiState()

}