package com.example.node_ems

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.node_ems.ui.theme.DashboardScreen
import com.example.node_ems.ui.theme.FormScreen
import com.example.node_ems.ui.theme.SyncQueueScreen
import com.example.node_ems.ui.theme.NodeEMSTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _ -> /* Manejo del permiso */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askNotificationPermission()

        setContent {
            NodeEMSTheme {
                MainScreen()
            }
        }
    }

    private fun askNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

@Composable
fun MainScreen() {
    var selectedScreen by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Turno") },
                    selected = selectedScreen == 0,
                    onClick = { selectedScreen = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Create, contentDescription = "Captura") },
                    label = { Text("ePCR") },
                    selected = selectedScreen == 1,
                    onClick = { selectedScreen = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Refresh, contentDescription = "Sincronizar") },
                    label = { Text("Sync") },
                    selected = selectedScreen == 2,
                    onClick = { selectedScreen = 2 }
                )
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            when (selectedScreen) {
                0 -> DashboardScreen()
                1 -> FormScreen()
                2 -> SyncQueueScreen()
            }
        }
    }
}