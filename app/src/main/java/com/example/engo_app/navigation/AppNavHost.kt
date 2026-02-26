package com.example.engo_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.engo_app.screens.*
import com.example.engo_app.ui.theme.ENGO_appTheme


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val routes = NavRoutes()

    NavHost(
        navController = navController,
        startDestination = routes.OnBoarding_Screen
    ) {
        composable(routes.OnBoarding_Screen) {
            OnBoardingScreen(navController)
        }

        composable(routes.Languages_Screen) {
            LanguagesScreen(navController)
        }

        composable(routes.Motivation_Screen) {
            MotivationScreen(navController)
        }

        composable(routes.Notification_Screen) {
            NotificationScreen(navController)
        }
    }
}

