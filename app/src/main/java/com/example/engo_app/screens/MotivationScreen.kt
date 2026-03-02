package com.example.engo_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.example.engo_app.components.ActionButton
import com.example.engo_app.components.BackButton
import com.example.engo_app.components.ListItem
import com.example.engo_app.data.Motivation
import com.example.engo_app.data.motivations
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.viewmodel.UserPreferencesViewModel

@Composable
fun MotivationScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel: UserPreferencesViewModel = viewModel(
        factory = UserPreferencesViewModel.Factory
    )

    val routes = NavRoutes()
    val selectedMotivations = remember { mutableStateListOf<Motivation>() }

    Scaffold(
        // BOTTOM BAR
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
                        navController.navigate(routes.Notification_Screen) {
                            launchSingleTop = true
                            popUpTo("notification_screen") { inclusive = true }
                        }
                        viewModel.saveMotivationList(selectedMotivations)
                    }
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
            /// PREVIOUS SCREEN BUTTON
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
                        text = stringResource(R.string.motivation_subtitle),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // MOTIVATIONS LIST
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(motivations) { motivation ->
                    ListItem(
                        isSelected = selectedMotivations.contains(motivation),
                        onClick = {
                            if (selectedMotivations.contains(motivation)) {
                                selectedMotivations.remove(motivation) // unselect
                            } else {
                                selectedMotivations.add(motivation) // select
                            }
                        },
                        text = stringResource(motivation.motivationNameId),
                        image = painterResource(motivation.motivationIconResourceId),
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotivationScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        MotivationScreen(navController = navController)
    }
}

