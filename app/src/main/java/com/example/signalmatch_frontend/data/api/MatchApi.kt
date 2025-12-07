package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.ApiPostMatchesRequest
import com.example.signalmatch_frontend.data.model.request.CancelMatchRequest
import com.example.signalmatch_frontend.data.model.response.ApiSuccessGlobalResponse
import com.example.signalmatch_frontend.data.model.response.CancelMatchResponse
import com.example.signalmatch_frontend.data.model.response.GetMatchResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchApi {

    @GET("/api/matches")
    suspend fun getMatch(): GetMatchResponse

    @POST("/api/matches/{matchId}/cancel")
    suspend fun cancelMatch(
        @Path("matchId") matchId: Int,
        @Body request: CancelMatchRequest
    ): CancelMatchResponse

    @POST("/api/matches")
    suspend fun postMatches (
        @Body request: ApiPostMatchesRequest
    ): ApiSuccessGlobalResponse<Long>

    @POST("/api/matches/{match-id}/reject")
    suspend fun postMatchesReject (
        @Path("match-id") matchId: Int,
        @Body request: CancelMatchRequest
    ): ApiSuccessGlobalResponse<Void>

    @POST("/api/matches/{match-id}/accept")
    suspend fun postMatchesAccept (
        @Path("match-id") matchId: Long,
    ): ApiSuccessGlobalResponse<Void>


}