package com.example.signalmatch_frontend.data.model.request

data class PresignFileRequest(
    val fileName: String,
    val contentType: String
)



data class PresignImageRequest(
    val fileName: String,
    val mimeType: String,
    val contentLength: Long
)