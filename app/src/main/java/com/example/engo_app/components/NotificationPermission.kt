package com.example.engo_app.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.engo_app.viewmodel.UserPreferencesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPermissionScreen() {
    var permissionGranted by remember { mutableStateOf(false) }
    var permissionRequested by remember { mutableStateOf(false) }

    val viewModel: UserPreferencesViewModel = viewModel(
        factory = UserPreferencesViewModel.Factory
    )

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Launcher for requesting permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
        permissionRequested = true

        viewModel.saveNotification(isGranted)
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            permissionGranted = true
            permissionRequested = true

            viewModel.saveNotification(true)
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