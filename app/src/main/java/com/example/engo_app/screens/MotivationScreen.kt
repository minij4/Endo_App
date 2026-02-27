package com.example.engo_app.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.data.Language
import com.example.engo_app.data.Motivation
import com.example.engo_app.data.motivations
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue

@Composable
fun MotivationScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()

    val selectedMotivations = remember { mutableStateListOf<Motivation>() }
    Scaffold(
        // Confirm button on the bottom
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(routes.Notification_Screen)

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
            /// Button to go back
            BackButton({ navController.popBackStack() })

            // TOP app bar
            /// PROGRESS BAR (NEED TO IMPLEMENT)

            Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(dimensionResource(R.dimen.logo_pic_size_small)),
                        painter = painterResource(R.drawable.engo_logo2),
                        contentDescription = "ENGO app logo"
                    )

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
            // Motivation items list

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(motivations) { motivation ->
                    MotivationItem(
                        onClick = {
                            if (selectedMotivations.contains(motivation)) {
                                selectedMotivations.remove(motivation) // unselect
                            } else {
                                selectedMotivations.add(motivation) // select
                            }
                        },
                        isSelected = selectedMotivations.contains(motivation),
                        motivations = motivation
                    )
                }
            }
        }
    }
}

// Motivation item composable
@Composable
fun MotivationItem(
    onClick: () -> Unit,
    motivations: Motivation,
    isSelected: Boolean,
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
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(motivations.motivationIconResourceId),
                contentDescription = null
            )
            Text(
                text = stringResource(motivations.motivationNameId),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true)
@Composable
fun MotivationScreenPreview() {
    ENGO_appTheme {
        val navController = rememberNavController()
        MotivationScreen(navController = navController)
    }
}

