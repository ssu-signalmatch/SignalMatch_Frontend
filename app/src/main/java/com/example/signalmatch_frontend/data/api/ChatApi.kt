package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.ApiPostChatsRoomsRequest
import com.example.signalmatch_frontend.data.model.request.ApiPostChatsRoomsMessagesRequest
import com.example.signalmatch_frontend.data.model.response.ApiPostChatsRoomsMessagesResponse
import com.example.signalmatch_frontend.data.model.response.ApiPostChatsRoomsResponse
import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsMessagesResponse
import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsResponse
import com.example.signalmatch_frontend.data.model.response.ApiSuccessGlobalResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {

    @POST("/api/chats/rooms") // 채팅방 생성
    suspend fun postChatsRooms (
        @Body body: ApiPostChatsRoomsRequest
    ): ApiSuccessGlobalResponse<ApiPostChatsRoomsResponse>

    @GET("/api/chats") // 채팅방 목록 조회
    suspend fun getChatsRooms (
    ): ApiSuccessGlobalResponse<ArrayList<ApiGetChatsRoomsResponse>>

    @POST("/api/chats/rooms/{room-id}/messages") // 메시지 전송
    suspend fun postChatsRoomsMessages (
        @Path("room-id") roomId: Long,
        @Body body: ApiPostChatsRoomsMessagesRequest
    ): ApiSuccessGlobalResponse<ApiPostChatsRoomsMessagesResponse>

    @GET("/api/chats/rooms/{room-id}/messages") // 메시지 조회
    suspend fun getChatsRoomsMessages (
        @Path("room-id") roomId: Long,
        @Query("lastMessageId") lastMessageId: Long?,
        @Query("size") size: Int?
    ): ApiSuccessGlobalResponse<ArrayList<ApiGetChatsRoomsMessagesResponse>>

    @DELETE("/api/chats/messages/{message-id}") // 메시지 삭제
    suspend fun deleteChatsMessages (
        @Path("message-id") messageId: Long
    ): ApiSuccessGlobalResponse<Void>

}