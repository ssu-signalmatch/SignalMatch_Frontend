package com.example.signalmatch_frontend.data.model.response

data class StartupCreateProfileResponse(
    val message: String?,
    val data: Data?,
    val success: Boolean
        ){
    data class Data(val startupId: Long)
}