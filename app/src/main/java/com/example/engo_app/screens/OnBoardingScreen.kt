package com.example.engo_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.engo_app.R
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue
import com.example.engo_app.ui.theme.EngoGray
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.components.ActionButton


@Composable
fun OnBoardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()

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
                    onClick = {
                        navController.navigate(routes.Languages_Screen) {
                            launchSingleTop = true
                            popUpTo("languages_screen") { inclusive = true }
                        }
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // LOGO PIC
                Image(
                    modifier = Modifier.size(dimensionResource(R.dimen.logo_pic_size)),
                    painter = painterResource(R.drawable.engo_logo),
                    contentDescription = "ENGO app logo"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // LOGO TEXT
                Text(
                    text = stringResource(R.string.onboarding_title),
                    style = MaterialTheme.typography.displayLarge,
                    color = EngoBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                // SUBTITLE TEXT
                Text(
                    text = stringResource(R.string.onboarding_subtitle),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    color = EngoGray,
                    modifier = modifier.fillMaxWidth(0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        OnBoardingScreen(navController = navController)
    }
}