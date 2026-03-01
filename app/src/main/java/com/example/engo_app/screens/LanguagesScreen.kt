package com.example.engo_app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.engo_app.data.Language
import com.example.engo_app.data.languages
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.components.ActionButton
import com.example.engo_app.components.ListItem
import com.example.engo_app.data.mainLanguage
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme

@Composable
fun LanguagesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()
    var selectedMainLanguage by remember { mutableStateOf<Language?>(null) }
    var selectedLanguages by remember { mutableStateOf<Language?>(null) }

    Scaffold(
        //BOTTOM BAR
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                // CONFIRM BUTTON ON THE BOTTOM
                ActionButton(
                    text = stringResource(R.string.confirm_button),
                    navController = navController,
                    onClick = {
                        navController.navigate(routes.Motivation_Screen)
                              },
                    modifier = modifier
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(30.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // LEARNING LANGUAGE CHOSE
            Text(
                text = stringResource(R.string.learning_language_subtitle),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            ListItem(
                isSelected = selectedMainLanguage == mainLanguage,
                onClick = { selectedMainLanguage = mainLanguage },
                text = stringResource(mainLanguage.languageNameId),
                image = painterResource(mainLanguage.languageIconResourceId),
                modifier = modifier
            )

            Spacer(modifier = Modifier.height(20.dp))

            // TRANSLATION LANGUAGE CHOSE
            Text(
                text = stringResource(R.string.translation_language_subtitle),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(languages) {
                    ListItem(
                        isSelected = selectedLanguages == it,
                        onClick = { selectedLanguages == it },
                        text = stringResource(it.languageNameId),
                        image = painterResource(it.languageIconResourceId),
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguagesScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        LanguagesScreen(navController = navController)
    }
}


