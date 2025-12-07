package com.example.signalmatch_frontend.data.model.response

data class ApiGetChatsRoomsResponse(
    val roomId: Long,
    val opponentId: Long,
    val opponentName: String?,
    val opponentProfileImageUrl: String?,
    val lastMessage: String?,
    val lastMessageTime: String?,
    val hasUnread: Boolean
)