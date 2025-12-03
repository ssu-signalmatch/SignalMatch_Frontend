package com.example.signalmatch_frontend.data.model.response

data class DocumentListResponse(
    val message: String,
    val data: List<DocumentListItem>,
    val success: Boolean
)

data class DocumentListItem(
    val documentId: Int,
    val objectKey: String,
    val url: String,
    val fileName: String
)