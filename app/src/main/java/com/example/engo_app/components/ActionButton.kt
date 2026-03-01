package com.example.engo_app.components

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
import com.example.engo_app.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.EngoBlue

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit
) {
    val routes = NavRoutes()
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = EngoBlue,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(56.dp)
    ) {
        Text(
            //text = stringResource(R.string.confirm_button),
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}