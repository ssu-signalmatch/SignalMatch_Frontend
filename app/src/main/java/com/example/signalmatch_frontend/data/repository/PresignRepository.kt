package com.example.signalmatch_frontend.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.example.signalmatch_frontend.data.api.DocumentApi
import com.example.signalmatch_frontend.data.api.S3Api
import com.example.signalmatch_frontend.data.api.UploadApi
import com.example.signalmatch_frontend.data.model.request.DocumentRequest
import com.example.signalmatch_frontend.data.model.request.PresignImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val s3Api: S3Api,
    private val uploadApi: UploadApi,
    private val documentApi: DocumentApi,
    private val contentResolver: ContentResolver
) {

    suspend fun uploadProfileImage(userId: Int, imageUri: Uri): String =
        withContext(Dispatchers.IO) {

            val mimeType = contentResolver.getType(imageUri) ?: "image/jpeg"
            val fileName = "profile_${System.currentTimeMillis()}.jpg"

            val bytes = contentResolver.openInputStream(imageUri)
                ?.use { it.readBytes() }
                ?: throw IllegalStateException("이미지 파일을 열 수 없습니다.")

            val presignResponse = s3Api.getPresignedUrl(
                PresignImageRequest(
                    contentLength = bytes.size.toLong(),
                    fileName = fileName,
                    mimeType = mimeType
                )
            )

            if (!presignResponse.success) {
                throw IllegalStateException(presignResponse.message)
            }

            val presignData = presignResponse.data

            val requestBody = bytes.toRequestBody(mimeType.toMediaTypeOrNull())
            val uploadResponse = uploadApi.uploadImageToS3(
                url = presignData.url,
                headers = presignData.headers,
                body = requestBody
            )

            if (!uploadResponse.isSuccessful) {
                throw IllegalStateException("S3 업로드 실패: ${uploadResponse.code()}")
            }

            val docResponse = documentApi.registerDocument(
                DocumentRequest(objectKey = presignData.key)
            )

            if (!docResponse.success) {
                throw IllegalStateException(docResponse.message ?: "문서 등록 실패")
            }

            return@withContext "https://signalmatch.s3.ap-northeast-2.amazonaws.com/${presignData.key}"
        }

}


