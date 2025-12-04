package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.DocumentRequest
import com.example.signalmatch_frontend.data.model.response.DocumentDeleteResponse
import com.example.signalmatch_frontend.data.model.response.DocumentListResponse
import com.example.signalmatch_frontend.data.model.response.DocumentResponse
import com.example.signalmatch_frontend.data.model.response.OtherDocumentListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DocumentApi {

    @POST("api/documents")
    suspend fun registerDocument(
        @Body request: DocumentRequest
    ): DocumentResponse

    @GET("api/documents")
    suspend fun getDocuments(): DocumentListResponse

    @DELETE("api/documents/{documentId}")
    suspend fun deleteDocument(
        @Path("documentId") documentId: Int
    ): DocumentDeleteResponse

    //다른 사용자 조회
    @GET("api/documents/{userId}")
    suspend fun getOtherDocuments(
        @Path("userId") userId: Int
    ): OtherDocumentListResponse

}
