package com.example.signalmatch_frontend.data.repository

import android.util.Log
import com.example.signalmatch_frontend.data.api.BookmarkApi
import com.example.signalmatch_frontend.data.model.request.AddBookmarkRequest
import com.example.signalmatch_frontend.data.model.response.AddBookmarkData
import com.example.signalmatch_frontend.data.model.response.TargetItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val bookmarkApi: BookmarkApi
) {
    companion object {
        private const val TAG = "BookmarkRepository"
    }

    //추가
    suspend fun addBookmark(targetUserId: Int): Result<AddBookmarkData> = runCatching {
        val response = bookmarkApi.addBookmark(
            AddBookmarkRequest(
                targetUserId = targetUserId
            )
        )

        if (!response.success) {
            Log.e(TAG, "addBookmark failed: ${response.message}")
            throw IllegalStateException(response.message)
        }

        response.data
    }

    //조회
    suspend fun getBookmarks(): Result<List<TargetItem>> = runCatching {
        val response = bookmarkApi.getBookmark()

        if (!response.success) {
            Log.e(TAG, "getBookmarks failed: ${response.message}")
            throw IllegalStateException(response.message)
        }

        response.data
    }

    //삭제
    suspend fun deleteBookmark(targetUserId: Int): Result<Unit> = runCatching {
        val response = bookmarkApi.deleteBookmark(targetUserId)

        if (!response.success) {
            Log.e(TAG, "deleteBookmark failed: ${response.message}")
            throw IllegalStateException(response.message)
        }

        Unit
    }
}