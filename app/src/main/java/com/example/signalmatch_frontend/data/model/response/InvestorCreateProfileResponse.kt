package com.example.signalmatch_frontend.data.model.response

data class InvestorCreateProfileResponse(
    val message: String?,
    val data: Data?,
    val success: Boolean
){
    data class Data(val investorId: Long)
}