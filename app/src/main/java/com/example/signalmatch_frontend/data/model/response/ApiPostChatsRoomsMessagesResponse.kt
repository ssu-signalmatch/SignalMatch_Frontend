
package com.example.signalmatch_frontend.data.model.response

data class ApiPostChatsRoomsMessagesResponse(
    val id: Long,
    val chatRoomId: Long,
    val senderRole: ChatSenderRole,
    val senderId: Long,
    val content: String,
    val deleted: Boolean
)
