package com.example.signalmatch_frontend.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.stringPreferencesKey


class UserPreference(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val KEY_PROFILE_COMPLETED = booleanPreferencesKey("profile_completed")
        private val KEY_USER_ROLE = stringPreferencesKey("user_role")   // 🔹 추가
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    val profileCompletedFlow: Flow<Boolean> =
        dataStore.data.map { prefs -> prefs[KEY_PROFILE_COMPLETED] ?: false }

    val userRoleFlow: Flow<String?> =
        dataStore.data.map { prefs -> prefs[KEY_USER_ROLE] }   // 🔹 추가

    val accessTokenFlow: Flow<String?> =
        dataStore.data.map { prefs -> prefs[KEY_ACCESS_TOKEN] }

    suspend fun setProfileCompleted(completed: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_PROFILE_COMPLETED] = completed
        }
    }

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { prefs ->
            prefs[KEY_ACCESS_TOKEN] = token
        }
    }

    suspend fun saveUserRole(role: String) {
        dataStore.edit { prefs ->
            prefs[KEY_USER_ROLE] = role
        }
    }
}

