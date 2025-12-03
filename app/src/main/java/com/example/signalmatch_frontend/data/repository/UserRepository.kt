package com.example.signalmatch_frontend.data.repository

import com.example.signalmatch_frontend.data.api.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi
) {
    suspend fun getMe() = userApi.getMe()
}