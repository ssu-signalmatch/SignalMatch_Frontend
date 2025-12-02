package com.example.signalmatch_frontend.data.model.request

import com.google.gson.annotations.SerializedName

data class DocumentRequest(
    @SerializedName("ObjectKey")
    val objectKey: String
)
