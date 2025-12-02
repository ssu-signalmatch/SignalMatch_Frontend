package com.example.signalmatch_frontend.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "settings")
    }

    private val LAST_UPDATED_DATE = stringPreferencesKey("last_updated_date")

    suspend fun saveLastUpdatedDate(date: String) {
        context.dataStore.edit { prefs ->
            prefs[LAST_UPDATED_DATE] = date
        }
    }

    val lastUpdatedDate: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[LAST_UPDATED_DATE]
    }


    private val KEY_CURRENT_USER_ID = intPreferencesKey("current_user_id")


    suspend fun saveLoginInfo(userId: Int, startupId: Int?) {
        context.dataStore.edit { prefs ->

            prefs[KEY_CURRENT_USER_ID] = userId

            if (startupId != null && startupId > 0) {
                val startupKey = intPreferencesKey("startup_id_$userId")
                prefs[startupKey] = startupId
            }
        }
    }

    val currentUserId: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_CURRENT_USER_ID] ?: -1
    }

    val currentStartupId: Flow<Int> = context.dataStore.data.map { prefs ->
        val userId = prefs[KEY_CURRENT_USER_ID] ?: return@map -1
        val startupKey = intPreferencesKey("startup_id_$userId")
        prefs[startupKey] ?: -1
    }

    suspend fun clearCurrentUser() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_CURRENT_USER_ID)
        }
    }
}
