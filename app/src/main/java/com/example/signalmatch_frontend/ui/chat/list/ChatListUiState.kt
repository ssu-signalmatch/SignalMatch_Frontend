package com.example.signalmatch_frontend.ui.chat.list

sealed class ChatListUiState {

    data object Loading: ChatListUiState()

    data class Success (
        val data: ArrayList<ChatListItem>?
    ): ChatListUiState()

    data class Error (
        val message: String
    ): ChatListUiState()

}