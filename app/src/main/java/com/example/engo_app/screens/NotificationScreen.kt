package com.example.engo_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.components.BackButton
import com.example.engo_app.components.DataStoreDebugScreen
import com.example.engo_app.components.NotificationPermissionScreen
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.viewmodel.UserPreferencesViewModel


@Composable
fun NotificationScreen(navController: NavController) {
    val routes = NavRoutes()
    val viewModel: UserPreferencesViewModel = viewModel(
        factory = UserPreferencesViewModel.Factory
    )

    Scaffold { padding ->
        Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(30.dp)
         ) {
            // PREVIOUS SCREEN BUTTON
            BackButton({ navController.popBackStack() })

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // LOGO
                Image(
                    modifier = Modifier.size(dimensionResource(R.dimen.logo_pic_size_small)),
                    painter = painterResource(R.drawable.engo_logo2),
                    contentDescription = "ENGO app logo"
                )
                // LOGO TEXT
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFEDEDED),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.notification_subtitle),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
            // NOTIFICATIONS PERMISSION POP OUT TABLE
            NotificationPermissionScreen()

            // DATA STORE OUTPUT
            DataStoreDebugScreen(viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        NotificationScreen(navController = navController)
    }
}
