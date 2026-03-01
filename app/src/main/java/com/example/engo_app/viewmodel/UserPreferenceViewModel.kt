package com.example.engo_app.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.engo_app.UserPreferencesApplication
import com.example.engo_app.data.Language
import com.example.engo_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class UserPreferencesViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel() {

    val notification: StateFlow<Boolean> =
        repository.notificationPermission
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                false
            )

    val learningLanguage: StateFlow<Language?> =
        repository.learningLanguage
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                null)

    val translationLanguage: StateFlow<Language?> =
        repository.translationLanguage
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                null)

    fun saveLanguages(
        learningLanguage: Language?,
        translationLanguage: Language?
    ) {
        viewModelScope.launch {
            repository.saveSelectedLanguages(
                learningLanguage,
                translationLanguage
            )
        }
    }

    fun saveNotification(enabled: Boolean) {
        viewModelScope.launch {
            repository.saveNotificationPermission(enabled)
        }
    }

    fun saveMotivationList(list: List<String>) {
        viewModelScope.launch {
            repository.saveMotivationList(list)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserPreferencesApplication)
                UserPreferencesViewModel(application.userPreferencesRepository)
            }
        }
    }
}