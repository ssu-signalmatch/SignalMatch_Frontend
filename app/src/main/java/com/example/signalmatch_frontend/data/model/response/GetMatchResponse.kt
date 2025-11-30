package com.example.signalmatch_frontend.data.model.response


data class GetMatchResponse(
    val message: String,
    val data: List<MatchData>,
    val success: Boolean
)

data class MatchData(
    val matchId: Int,
    val startupId: Int,
    val investorId: Int,
    val status: String,
    val matchedAt: String,
    val endedAt: String?,
    val requestedBy: String
)
