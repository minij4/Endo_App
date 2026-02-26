package com.example.engo_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import com.example.engo_app.R
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import com.example.engo_app.ui.theme.EngoBlue
import com.example.engo_app.ui.theme.EngoGray
import androidx.compose.ui.text.TextStyle
import androidx.navigation.compose.rememberNavController


@Composable
fun onboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val routes = NavRoutes()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Image(
            modifier = Modifier.size(dimensionResource(R.dimen.logo_pic_size)),
            painter = painterResource(R.drawable.engo_logo),
            contentDescription = "ENGO app logo"
        )

        Spacer(modifier = Modifier.height(8.dp))
        OnboardingText(
            textRes = R.string.onboarding_title,
            textStyle = MaterialTheme.typography.displayLarge,
            color = EngoBlue
        )

        Spacer(modifier = Modifier.height(16.dp))

        OnboardingText(
            textRes = R.string.onboarding_subtitle,
            textStyle = MaterialTheme.typography.displaySmall,
            color = EngoGray
        )



        Spacer(modifier = Modifier.weight(1f))
        Button(
            // screen change
            onClick = {
                navController.navigate(routes.Languages_Screen)
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
                text = stringResource(R.string.onboarding_start_button),
                style = MaterialTheme.typography.displaySmall
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun OnboardingText(
    @StringRes textRes: Int,
    textStyle: TextStyle,
    color: Color,
    widthFraction: Float = 0.7f,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textRes),
        style = textStyle,
        modifier = modifier.fillMaxWidth(widthFraction),
        textAlign = TextAlign.Center,
        color = color
    )
}

