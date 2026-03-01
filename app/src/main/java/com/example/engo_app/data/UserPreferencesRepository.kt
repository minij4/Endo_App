package com.example.engo_app.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "onboardingsettings")

object DataStoreKeys {
    val LEARNING_LANGUAGE = intPreferencesKey("learning_language")
    val TRANSLATION_LANGUAGE = intPreferencesKey("translation_language")
    val MOTIVATION_LIST= stringSetPreferencesKey("motivation_list")
    val NOTIFICATION = booleanPreferencesKey("notification")
}

class UserPreferenceRepository(private val context: Context) {

    // READ
    val notificationPermission: Flow<Boolean>
        get() = context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.NOTIFICATION] ?: false
        }

    val learningLanguage: Flow<Language?>
        get() = context.dataStore.data.map { preferences ->
            val id = preferences[DataStoreKeys.LEARNING_LANGUAGE]
            languages.find { it.languageNameId == id }
        }

    val translationLanguage: Flow<Language?>
        get() = context.dataStore.data.map { preferences ->
            val id = preferences[DataStoreKeys.TRANSLATION_LANGUAGE]
            languages.find { it.languageNameId == id }
        }

    // WRITE
    suspend fun saveSelectedLanguages(
        learningLanguage: Language?,
        translationLanguage: Language?
    ) {
        context.dataStore.edit { prefs ->
            learningLanguage?.let {
                prefs[DataStoreKeys.LEARNING_LANGUAGE] = it.languageNameId
            }
            translationLanguage?.let {
                prefs[DataStoreKeys.TRANSLATION_LANGUAGE] = it.languageNameId
            }
        }
    }

    suspend fun saveMotivationList(motivationList: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.MOTIVATION_LIST] = motivationList.toSet()
        }
    }

    suspend fun saveNotificationPermission(notificationEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.NOTIFICATION] = notificationEnabled
        }

        Log.d("DataStoreTest", "Saved notification = $notificationEnabled")
    }
}
