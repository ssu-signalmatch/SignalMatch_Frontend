package com.example.signalmatch_frontend.data.model.request

data class PresignImageRequest(
    val fileName: String,
    val mimeType: String,
    val contentLength: Long
)

data class IrPresignRequest(
    val fileName: String,
    val mimeType: String,
    val contentLength: Long
)

