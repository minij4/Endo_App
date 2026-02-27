package com.example.engo_app.screens




import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.engo_app.R
import com.example.engo_app.data.saveNotificationPermission
import com.example.engo_app.navigation.NavRoutes
import com.example.engo_app.ui.theme.ENGO_appTheme
import kotlinx.coroutines.launch


@Composable
fun NotificationScreen(navController: NavController) {
    val routes = NavRoutes()

    Scaffold { padding ->

        Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(30.dp)
         ) {
            BackButton({ navController.popBackStack() })

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
                        text = stringResource(R.string.notification_subtitle),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }


            NotificationPermissionScreen()
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

//// NOTIFICATION PERMISSION

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPermissionScreen() {
    var permissionGranted by remember { mutableStateOf(false) }
    var permissionRequested by remember { mutableStateOf(false) }


    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Launcher for requesting permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
        permissionRequested = true

        scope.launch {
           saveNotificationPermission(context, isGranted)
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            permissionGranted = true
            permissionRequested = true

            scope.launch {
                saveNotificationPermission(context, true)
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))


    if (permissionRequested) {
        Text(
            text = if (permissionGranted) {
                "Permission Granted"
            } else {
                "Permission Denied"
            }
        )
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
