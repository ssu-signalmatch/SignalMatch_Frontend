package com.example.signalmatch_frontend.data.model.response

enum class ChatSenderRole (
    val value: String,
    val code : Int
) {
    INVESTOR (
        value = "INVESTOR",
        code = 0
    ),
    STARTUP (
        value = "STARTUP",
        code = 1
    )
}