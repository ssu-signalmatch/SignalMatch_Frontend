package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.BookmarkApi
import com.example.signalmatch_frontend.data.model.request.AddBookmarkRequest
import com.example.signalmatch_frontend.data.model.response.AddBookmarkResponse
import com.example.signalmatch_frontend.data.model.response.DeleteBookmarkResponse
import com.example.signalmatch_frontend.data.model.response.GetBookmarkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val api: BookmarkApi
) {
    suspend fun getBookmark(): GetBookmarkResponse {
        return api.getBookmark()
    }

    suspend fun addBookmark(request: AddBookmarkRequest): AddBookmarkResponse {
        return api.addBookmark(request)
    }

    suspend fun deleteBookmark(targetUserId: Int): DeleteBookmarkResponse {
        return api.deleteBookmark(targetUserId)
    }
}
