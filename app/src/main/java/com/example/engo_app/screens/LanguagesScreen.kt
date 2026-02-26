package com.example.engo_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment

import com.example.engo_app.data.Language
import com.example.engo_app.data.languages

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue



@Composable
fun LanguagesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(routes.Motivation_Screen)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EngoBlue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                ) {
                    Text(
                        text = stringResource(R.string.learning_language_button),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
    ) { padding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(30.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.learning_language_subtitle),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            LanguageItem(languages.first())

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.translation_language_subtitle),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(languages) {
                    LanguageItem(languages = it)
                }
            }
        }
    }
}

@Composable
fun LanguageItem(
    languages: Language,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Image(
                modifier = modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small))
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(languages.languageIconResourceId),

                // Content Description is not needed here - image is decorative, and setting a null content
                // description allows accessibility services to skip this element during navigation.

                contentDescription = null
            )
            Text(
                text = stringResource(languages.languageNameId),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                modifier = modifier.padding(start = 20.dp)
            )
        }
    }
    Spacer(modifier = modifier.height(10.dp))
}

