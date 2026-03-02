package com.example.engo_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.engo_app.viewmodel.UserPreferencesViewModel

@Composable
fun DataStoreDebugScreen(
    viewModel: UserPreferencesViewModel
) {
    // Collect StateFlow iš ViewModel
    val notification by viewModel.notification.collectAsState(initial = null)
    val learningLanguage by viewModel.learningLanguage.collectAsState(initial = null)
    val translationLanguage by viewModel.translationLanguage.collectAsState(initial = null)
    val motivations by viewModel.motivations.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "User Preferences (DataStore):",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Notifications enabled: $notification")



        Text(
            text = learningLanguage?.let {
                "Learning language: ${stringResource(it.languageNameId)}"
            } ?: "Learning language: Not set"
        )

        Text(
            text = translationLanguage?.let {
                "Translation language: ${stringResource(it.languageNameId)}"
            } ?: "Translation language: Not set"
        )

        Text(text = "Motivations:")
        motivations.forEach { motivation ->
            Text(text = stringResource(motivation.motivationNameId))
        }
    }
}