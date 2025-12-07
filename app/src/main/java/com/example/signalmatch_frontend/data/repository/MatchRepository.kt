package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.MatchApi
import com.example.signalmatch_frontend.data.model.request.ApiPostMatchesRequest
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

    suspend fun requestMatch (
        investorId: Long,
        startupId: Long
    ): Long? {
        return api.postMatches(
            request = ApiPostMatchesRequest(
                investorId = investorId,
                startupId = startupId
            )
        ).data
    }

    suspend fun rejectMatch (
        matchId: Long,
        reasonCode: MatchReasonCode,
        reasonText: String?
    ): Void? {
        return api.postMatchesReject(
            matchId = matchId.toInt(),
            request = CancelMatchRequest(
                reasonCode = reasonCode,
                reasonText = reasonText
            )
        ).data
    }

    suspend fun acceptMatch (
        matchId: Long
    ): Void? {
        return api.postMatchesAccept(
            matchId = matchId
        ).data
    }

}
