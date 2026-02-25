package com.example.engo_app.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val LEARNING_LANGUAGE = stringPreferencesKey("learning_language")
        val TRANSLATION_LANGUAGE = stringPreferencesKey("translation_language")
        val MOTIVATION_LIST= stringSetPreferencesKey("motivation_list")
        val NOTIFICATION = booleanPreferencesKey("notification")
    }
    // READ


    // WRITE

    suspend fun saveLearningLanguage(learningLanguage: String) {
        dataStore.edit { preferences ->
            preferences[LEARNING_LANGUAGE] = learningLanguage
        }
    }
    suspend fun saveTranslationLanguage(translationLanguage: String) {
        dataStore.edit { preferences ->
            preferences[TRANSLATION_LANGUAGE] = translationLanguage
        }
    }
    suspend fun saveMotivationList(motivationList: List<String>) {
        dataStore.edit { preferences ->
            preferences[MOTIVATION_LIST] = motivationList.toSet()
        }
    }
    suspend fun setNotification(notificationEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION] = notificationEnabled
        }
    }

    // Handle errors

    val isLearningLanguage: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading learning language.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LEARNING_LANGUAGE] ?: "English"
        }

    val isTranslationLanguage: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading translation language.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[TRANSLATION_LANGUAGE] ?: "Lithuanian"
        }

    val isNotification: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading is notification true or false.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[NOTIFICATION] ?: true
        }

}