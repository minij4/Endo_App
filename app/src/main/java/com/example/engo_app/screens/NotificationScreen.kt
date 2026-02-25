package com.example.engo_app.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.engo_app.navigation.NavRoutes

@Composable
fun NotificationScreen(navController: NavController) {
    val routes = NavRoutes()

    Text(
        text = "Notification Screen"
    )
}