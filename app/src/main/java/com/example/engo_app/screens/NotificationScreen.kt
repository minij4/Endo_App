package com.example.engo_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue


@Composable
fun NotificationScreen(navController: NavController) {
    val routes = NavRoutes()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)
    ) {
        BackButton({ navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

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
                    text = stringResource(R.string.notification_subtitle),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color(0xFFEDEDED),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.notification_agreement),
                style = MaterialTheme.typography.displayMedium,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp)
            )
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = EngoBlue
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
            ) {
                Text(
                    text = stringResource(R.string.notification_agree),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
            ) {
                Text(
                    text = stringResource(R.string.notification_disagree),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }


    


    }
}


@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black
        )
    }
}