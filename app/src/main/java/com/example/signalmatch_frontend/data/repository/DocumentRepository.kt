package com.example.signalmatch_frontend.data.repository

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import com.example.signalmatch_frontend.data.api.DocumentApi
import com.example.signalmatch_frontend.data.api.S3Api
import com.example.signalmatch_frontend.data.api.UploadApi
import com.example.signalmatch_frontend.data.model.request.DocumentRequest
import com.example.signalmatch_frontend.data.model.request.IrPresignRequest
import com.example.signalmatch_frontend.data.model.response.DocumentListItem
import com.example.signalmatch_frontend.data.model.response.DocumentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import javax.inject.Inject

class DocumentRepository @Inject constructor(
    private val s3Api: S3Api,
    private val documentApi: DocumentApi,
    private val uploadApi: UploadApi
) {

    companion object {
        private const val TAG = "DocumentRepository"
    }

    suspend fun registerDocument(objectKey: String): DocumentResponse =
        documentApi.registerDocument(DocumentRequest(objectKey))

    suspend fun uploadIrDocument(
        contentResolver: ContentResolver,
        fileUri: Uri,
        startupId: Int
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val originalFileName = getFileName(contentResolver, fileUri)
            val fileNameWithExt = originalFileName ?: "ir_${startupId}.pdf"
            val mimeType = "application/pdf"
            val contentLength = getContentLength(contentResolver, fileUri)

            val presignResponse = s3Api.getIrPresignedUrl(
                startupId = startupId,
                request = IrPresignRequest(
                    fileName = fileNameWithExt,
                    mimeType = mimeType,
                    contentLength = contentLength
                )
            )

            if (!presignResponse.success || presignResponse.data == null) {
                Log.e(TAG, "IR presign failed: ${presignResponse.message}")
                return@withContext Result.failure(
                    IllegalStateException(presignResponse.message ?: "IR presign 실패")
                )
            }

            val dataMap = presignResponse.data as? Map<*, *>
                ?: return@withContext Result.failure(
                    IllegalStateException("IR presign data 형식이 예상과 다릅니다.")
                )

            val presignedUrl = (dataMap["presignedUrl"] ?: dataMap["url"]) as? String
                ?: return@withContext Result.failure(
                    IllegalStateException("presignedUrl 을 찾을 수 없습니다.")
                )

            val objectKey = (dataMap["objectKey"] ?: dataMap["key"]) as? String
                ?: return@withContext Result.failure(
                    IllegalStateException("objectKey 를 찾을 수 없습니다.")
                )

            val bytes = contentResolver.openInputStream(fileUri)
                ?.use { it.readBytes() }
                ?: return@withContext Result.failure(Exception("파일을 열 수 없습니다."))

            val requestBody = RequestBody.create(mimeType.toMediaType(), bytes)

            val headers = (dataMap["headers"] as? Map<*, *>)?.mapNotNull { (k, v) ->
                if (k is String && v is String) k to v else null
            }?.toMap() ?: emptyMap()

            val uploadResponse = uploadApi.uploadImageToS3(
                url = presignedUrl,
                headers = headers,
                body = requestBody
            )

            if (!uploadResponse.isSuccessful) {
                Log.e(TAG, "S3 upload failed: ${uploadResponse.code()}")
                return@withContext Result.failure(
                    IllegalStateException("S3 업로드 실패: ${uploadResponse.code()}")
                )
            }

            val registerResponse = documentApi.registerDocument(DocumentRequest(objectKey))

            if (!registerResponse.success) {
                Log.e(TAG, "Register document failed: ${registerResponse.message}")
                return@withContext Result.failure(
                    IllegalStateException(registerResponse.message ?: "문서 등록 실패")
                )
            }

            Result.success(Unit)

        } catch (e: Exception) {
            Log.e(TAG, "uploadIrDocument error", e)
            Result.failure(e)
        }
    }


    private fun getContentLength(contentResolver: ContentResolver, uri: Uri): Long {
        return contentResolver.openInputStream(uri)?.use { it.available().toLong() } ?: 0L
    }


    private fun getFileName(contentResolver: ContentResolver, uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (it.moveToFirst() && nameIndex != -1) {
                return it.getString(nameIndex) //
            }
        }
        return null
    }


    suspend fun getDocuments(): Result<List<DocumentListItem>> = runCatching {
        val response = documentApi.getDocuments()
        if (!response.success) throw IllegalStateException(response.message)
        response.data
    }


    suspend fun deleteDocument(documentId: Int): Result<Unit> = runCatching {
        val response = documentApi.deleteDocument(documentId)

        if (!response.success) {
            throw IllegalStateException(response.message)
        }

        Unit
    }

}