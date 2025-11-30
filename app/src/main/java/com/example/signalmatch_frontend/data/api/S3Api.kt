package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.PresignImageRequest
import com.example.signalmatch_frontend.data.model.response.PresignImageResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface S3Api {
    @POST("/api/s3/profile-image/presign")
    suspend fun getPresignedUrl(@Body body: PresignImageRequest): PresignImageResponse
}