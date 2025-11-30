package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.model.request.CancelMatchRequest
import com.example.signalmatch_frontend.data.model.response.CancelMatchResponse
import com.example.signalmatch_frontend.data.model.response.GetMatchResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchApi {

    @GET("/api/matches")
    suspend fun getMatch(): GetMatchResponse

    @POST("api/matches/{matchId}/cancel")
    suspend fun cancelMatch(
        @Path("matchId") matchId: Int,
        @Body request: CancelMatchRequest
    ): CancelMatchResponse

}