package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.ChatApi
import com.example.signalmatch_frontend.data.model.request.ApiPostChatsRoomsMessagesRequest
import com.example.signalmatch_frontend.data.model.request.ApiPostChatsRoomsRequest
import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsMessagesResponse
import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsResponse
import com.example.signalmatch_frontend.data.model.response.ApiPostChatsRoomsMessagesResponse
import com.example.signalmatch_frontend.data.model.response.ApiPostChatsRoomsResponse
import javax.inject.Inject

class ChatRepository
@Inject constructor (
    private val chatApi: ChatApi
) {

    suspend fun createChatRoom (
        startupId: Long,
        investorId: Long
    ): ApiPostChatsRoomsResponse? {
        return chatApi.postChatsRooms(
            body = ApiPostChatsRoomsRequest(
                startupId = startupId,
                investorId = investorId
            )
        ).data
    }

    suspend fun getChatRoomList (
    ): ArrayList<ApiGetChatsRoomsResponse>? {
        return chatApi.getChatsRooms().data
    }

    suspend fun sendMessage (
        roomId: Long,
        content: String
    ): ApiPostChatsRoomsMessagesResponse? {
        return chatApi.postChatsRoomsMessages(
            roomId = roomId,
            body = ApiPostChatsRoomsMessagesRequest(
                content = content
            )
        ).data
    }

    suspend fun getMessages (
        roomId: Long,
        lastMessageId: Long? = null,
        size: Int? = 50
    ): ArrayList<ApiGetChatsRoomsMessagesResponse>? {
        return chatApi.getChatsRoomsMessages(
            roomId = roomId,
            lastMessageId = lastMessageId,
            size = size
        ).data
    }

    suspend fun deleteMessage (
        messageId: Long
    ): Void? {
        return chatApi.deleteChatsMessages(
            messageId = messageId
        ).data
    }

}