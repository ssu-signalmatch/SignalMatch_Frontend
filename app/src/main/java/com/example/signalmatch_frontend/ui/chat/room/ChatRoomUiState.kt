package com.example.signalmatch_frontend.ui.chat.room

import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsMessagesResponse

sealed class ChatRoomUiState {

    data object Loading: ChatRoomUiState()

    data class Success (
        val data: ArrayList<ApiGetChatsRoomsMessagesResponse>?
    ): ChatRoomUiState()

    data class Error (
        val message: String
    ): ChatRoomUiState()

}