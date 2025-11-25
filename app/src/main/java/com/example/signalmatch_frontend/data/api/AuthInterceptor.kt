package com.example.signalmatch_frontend.data.api

import com.example.signalmatch_frontend.data.local.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userPreference: UserPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = runBlocking {
            userPreference.accessTokenFlow.first()
        }

        if (token.isNullOrBlank()) {
            return chain.proceed(original)
        }

        val newRequest = original.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}
