package com.example.signalmatch_frontend.data.model.response

import com.google.gson.annotations.SerializedName

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
