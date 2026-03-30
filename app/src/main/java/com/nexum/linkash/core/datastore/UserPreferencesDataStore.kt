package com.nexum.linkash.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_ID      = stringPreferencesKey("user_id")
        val USER_EMAIL   = stringPreferencesKey("user_email")
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN] ?: false }
    val userId: Flow<String?>     = dataStore.data.map { it[USER_ID] }
    val userEmail: Flow<String?>  = dataStore.data.map { it[USER_EMAIL] }

    suspend fun saveSession(userId: String, email: String) {
        dataStore.edit {
            it[IS_LOGGED_IN] = true
            it[USER_ID]      = userId
            it[USER_EMAIL]   = email
        }
    }

    suspend fun clearSession() {
        dataStore.edit {
            it[IS_LOGGED_IN] = false
            it.remove(USER_ID)
            it.remove(USER_EMAIL)
        }
    }
}
