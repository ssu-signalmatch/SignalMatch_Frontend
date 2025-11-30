package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.AddBookmarkRequest
import com.example.signalmatch_frontend.data.model.response.AddBookmarkResponse
import com.example.signalmatch_frontend.data.model.response.DeleteBookmarkResponse
import com.example.signalmatch_frontend.data.model.response.GetBookmarkResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkApi {
    @POST("/api/bookmarks")
    suspend fun addBookmark(@Body request: AddBookmarkRequest): AddBookmarkResponse

    @GET("/api/bookmarks")
    suspend fun getBookmark(): GetBookmarkResponse

    @DELETE("/api/bookmarks/{targetUserId}")
    suspend fun deleteBookmark(
        @Path("targetUserId") targetUserId: Int
    ): DeleteBookmarkResponse

}