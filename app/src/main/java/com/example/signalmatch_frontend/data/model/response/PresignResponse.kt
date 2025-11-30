package com.example.signalmatch_frontend.data.model.response

data class PresignFileResponse(
    val uploadUrl: String,
    val fileUrl: String
)

data class PresignImageResponse(
    val message: String,
    val data: PresignImageData,
    val success: Boolean
)

data class PresignImageData(
    val url: String,
    val method: String,
    val headers: Map<String, String>,
    val key: String
)