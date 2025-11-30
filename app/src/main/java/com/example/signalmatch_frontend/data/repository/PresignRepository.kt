package com.example.signalmatch_frontend.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.example.signalmatch_frontend.data.api.S3Api
import com.example.signalmatch_frontend.data.model.request.PresignImageRequest
import com.example.signalmatch_frontend.data.model.response.PresignImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresignRepository @Inject constructor(
    private val s3Api: S3Api,
    private val okHttpClient: OkHttpClient
) {

    suspend fun uploadProfileImage(
        contentResolver: ContentResolver,
        imageUri: Uri,
        userId: Int
    ): String = withContext(Dispatchers.IO) {
        val mimeType = contentResolver.getType(imageUri) ?: "image/png"

        val bytes = contentResolver.openInputStream(imageUri)?.use { input ->
            input.readBytes()
        } ?: throw IOException("이미지 파일을 읽을 수 없습니다")

        val contentLength = bytes.size.toLong()

        val fileName = "profile_user_${userId}_${System.currentTimeMillis()}.png"

        val presignRequest = PresignImageRequest(
            fileName = fileName,
            mimeType = mimeType,
            contentLength = contentLength
        )

        val presignResponse: PresignImageResponse = s3Api.getPresignedUrl(presignRequest)

        if (!presignResponse.success) {
            throw IOException("Presign 요청 실패: ${presignResponse.message}")
        }

        val data = presignResponse.data

        val requestBody = bytes.toRequestBody(mimeType.toMediaType())

        val requestBuilder = Request.Builder()
            .url(data.url)
            .method(data.method, requestBody)

        data.headers.forEach { (key, value) ->
            requestBuilder.header(key, value)
        }

        val request = requestBuilder.build()

        val response = okHttpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IOException("S3 업로드 실패: code=${response.code}, msg=${response.message}")
        }

        response.close()

        val publicUrl = data.url.substringBefore("?")

        return@withContext publicUrl
    }
}