package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.MatchApi
import com.example.signalmatch_frontend.data.model.request.CancelMatchRequest
import com.example.signalmatch_frontend.data.model.request.MatchReasonCode
import com.example.signalmatch_frontend.data.model.response.CancelMatchResponse
import com.example.signalmatch_frontend.data.model.response.GetMatchResponse
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val api: MatchApi
) {
    suspend fun getMatch(): GetMatchResponse {
        return api.getMatch()
    }
    suspend fun cancelMatch(
        matchId: Int,
        reasonCode: MatchReasonCode,
        reasonText: String?
    ): CancelMatchResponse {
        val request = CancelMatchRequest(
            reasonCode = reasonCode,
            reasonText = reasonText
        )
        return api.cancelMatch(
            matchId = matchId,
            request = request
        )
    }
}
