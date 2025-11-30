package com.example.signalmatch_frontend.data.model.request

enum class MatchReasonCode {
    NOT_INTERESTED,          // 관심 없음
    OUT_OF_SCOPE,            // 산업/단계/지역 불일치
    TIMING,                  // 타이밍 이슈
    DUPLICATE,               // 중복/이미 접촉 중
    TERMS_UNACCEPTABLE,      // 조건 불만족
    SPAM_OR_ABUSE,           // 스팸/부적절
    MISTAKE,                 // 실수로 요청
    OTHER                    // 기타
}

data class CancelMatchRequest(
    val reasonCode: MatchReasonCode,
    val reasonText: String? = null
)
