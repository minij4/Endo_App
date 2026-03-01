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
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore by preferencesDataStore(name = "onboardingsettings")

object DataStoreKeys {
    val LEARNING_LANGUAGE = intPreferencesKey("learning_language")
    val TRANSLATION_LANGUAGE = intPreferencesKey("translation_language")
    val MOTIVATION_LIST= stringSetPreferencesKey("motivation_list")
    val NOTIFICATION = booleanPreferencesKey("notification")
}

    // READ
    fun getNotificationPermission(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.NOTIFICATION] ?: false
        }
    }
    fun getLearningLanguage(context: Context): Flow<Language?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[DataStoreKeys.LEARNING_LANGUAGE]
            languages.find { it.languageNameId == id }
        }
    }
    fun getTranslationLanguage(context: Context): Flow<Language?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[DataStoreKeys.TRANSLATION_LANGUAGE]
            languages.find { it.languageNameId == id }
        }
    }
    // WRITE
    suspend fun saveLearningLanguage(context: Context, learningLanguage: Language) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LEARNING_LANGUAGE] = learningLanguage.languageNameId
        }
    }
    suspend fun saveTranslationLanguage(context: Context, translationLanguage: Language) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.TRANSLATION_LANGUAGE] = translationLanguage.languageNameId
        }
    }
    suspend fun saveMotivationList(context: Context, motivationList: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.MOTIVATION_LIST] = motivationList.toSet()
        }
    }
    suspend fun saveNotificationPermission(context: Context, notificationEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.NOTIFICATION] = notificationEnabled
        }

        Log.d("DataStoreTest", "Saved notification = $notificationEnabled")
    }

/// ERRORS
