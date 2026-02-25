package com.example.engo_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.engo_app.R
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.EngoBlue

@Composable
fun MotivationScreen(navController: NavController) {
    val routes = NavRoutes()

    Column {
        Text(
            text = "Motivation Screen"
        )
        Button(
            // screen change
            onClick = {
                navController.navigate(routes.Notification_Screen)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = EngoBlue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)  // 80% screen
                .height(56.dp)
        ) {
            Text(
                text = "NEXT",
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}