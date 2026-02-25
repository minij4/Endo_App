package com.example.engo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.engo_app.screens.*


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val routes = NavRoutes()

    NavHost(
        navController = navController,
        startDestination = routes.onBoarding_Screen
    ) {
        composable(routes.onBoarding_Screen) {
            onboardingScreen(navController)
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