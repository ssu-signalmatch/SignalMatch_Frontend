package com.example.signalmatch_frontend.data.api

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.PUT
import retrofit2.http.Url


interface UploadApi {
    @PUT
    suspend fun uploadImageToS3(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: RequestBody
    ): Response<Unit>

}
