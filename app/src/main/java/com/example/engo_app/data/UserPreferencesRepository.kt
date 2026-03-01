package com.example.engo_app.data

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val LEARNING_LANGUAGE = intPreferencesKey("learning_language")
        val TRANSLATION_LANGUAGE = intPreferencesKey("translation_language")
        val MOTIVATION_LIST= stringSetPreferencesKey("motivation_list")
        val NOTIFICATION = booleanPreferencesKey("notification")
    }

    // READ
    val notificationPermission: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[NOTIFICATION] ?: true
        }

    val learningLanguage: Flow<Language?> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val id = preferences[LEARNING_LANGUAGE]
            languages.find { it.languageNameId == id }
        }

    val translationLanguage: Flow<Language?> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val id = preferences[TRANSLATION_LANGUAGE]
            languages.find { it.languageNameId == id }
        }


    // WRITE
    suspend fun saveSelectedLanguages(
        learningLanguage: Language?,
        translationLanguage: Language?
    ) {
        dataStore.edit { prefs ->
            learningLanguage?.let {
                prefs[LEARNING_LANGUAGE] = it.languageNameId
            }
            translationLanguage?.let {
                prefs[TRANSLATION_LANGUAGE] = it.languageNameId
            }
        }
        Log.d("DataStoreTest", "Saved languges")
    }

    suspend fun saveMotivationList(motivationList: List<String>) {
        dataStore.edit { preferences ->
            preferences[MOTIVATION_LIST] = motivationList.toSet()
        }

        Log.d("DataStoreTest", "Saved motivations = $motivationList")
    }

    suspend fun saveNotificationPermission(notificationEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION] = notificationEnabled
        }

        Log.d("DataStoreTest", "Saved notification = $notificationEnabled")
    }
}
