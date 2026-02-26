package com.example.engo_app.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
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
import com.example.engo_app.data.mainLanguage
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue



@Composable
fun LanguagesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()
    var selectedMainLanguage by remember { mutableStateOf<Language?>(null) }
    var selectedLanguage by remember { mutableStateOf<Language?>(null) }

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
                        text = stringResource(R.string.confirm_button),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
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

            Text(
                text = stringResource(R.string.learning_language_subtitle),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            LanguageItem({selectedMainLanguage = mainLanguage},isSelected = selectedMainLanguage == mainLanguage, mainLanguage)

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
                    LanguageItem({selectedLanguage = it},isSelected = selectedLanguage == it, languages = it)
                }
            }
        }
    }
}

// LIST ITEM
@Composable
fun LanguageItem(
    onClick: () -> Unit,
    isSelected: Boolean,
    languages: Language,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected)
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        else null
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

@Preview(showBackground = true)
@Composable
fun LanguagesScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        LanguagesScreen(navController = navController)
    }
}


