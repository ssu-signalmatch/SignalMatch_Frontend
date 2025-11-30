package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.local.TokenHolder
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.encodedPath

        if (url.contains("/api/auth/login") || url.contains("/api/auth/signup")) {
            return chain.proceed(original)
        }

        val token = TokenHolder.accessToken

        if (token.isNullOrBlank()) {
            return chain.proceed(original)
        }

        val newRequest = original.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}
