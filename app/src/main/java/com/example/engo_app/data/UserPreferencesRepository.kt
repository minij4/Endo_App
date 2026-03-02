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
    val motivationsFlow: Flow<List<Motivation>> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val savedIds = preferences[MOTIVATION_LIST] ?: emptySet()

            motivations.filter {
                savedIds.contains(it.motivationNameId.toString())
            }
        }

    // WRITE
    suspend fun saveSelectedLanguages(
        learningLanguage: Language?,
        translationLanguage: Language?
    ) {
        dataStore.edit { prefs ->
            Log.d("DataStoreTest", "Saved languges")
            learningLanguage?.let {
                prefs[LEARNING_LANGUAGE] = it.languageNameId
                Log.d("DataStoreTest", "$it.languageNameId")
            }
            translationLanguage?.let {
                prefs[TRANSLATION_LANGUAGE] = it.languageNameId
                Log.d("DataStoreTest", "$it.languageNameId")
            }
        }

    }

    suspend fun saveMotivationList(motivationList: List<Motivation>) {
        val idSet = motivationList.map { it.motivationNameId.toString() }.toSet()

        dataStore.edit { preferences ->
            preferences[MOTIVATION_LIST] = idSet
        }

        Log.d("DataStoreTest", "Saved motivations = $idSet")
    }

    suspend fun saveNotificationPermission(notificationEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION] = notificationEnabled
        }

        Log.d("DataStoreTest", "Saved notification = $notificationEnabled")
    }
}
