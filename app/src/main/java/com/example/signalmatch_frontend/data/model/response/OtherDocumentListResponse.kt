package com.example.signalmatch_frontend.data.model.response

data class OtherDocumentListResponse(
    val message: String?,
    val data: List<OtherDocumentData>?,
    val success: Boolean
)

data class OtherDocumentData(
    val documentId: Int,
    val objectKey: String,
    val url: String,
    val fileName: String
)
