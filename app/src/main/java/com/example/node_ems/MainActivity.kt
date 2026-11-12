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

    // 1. SEGURIDAD: Prepara el sistema para pedir permisos (Notificaciones)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _ -> /* Manejo del permiso */ }

    // 2. PUNTO DE ENTRADA: Metodo principal al arrancar la app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission() // Valida permisos de inmediato

        // 3. INTERFAZ: Inicia Jetpack Compose inyectando el tema visual de Node-EMS
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
    // 4. ESTADO REACTIVO: Guarda qué pestaña está activa (0=Turno, 1=ePCR, 2=Sync)
    var selectedScreen by remember { mutableIntStateOf(0) }

    // 5. ESTRUCTURA UI: Scaffold encargado de la barra de navegación inferior
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
            // 6. RENDERIZADO DINÁMICO: Carga la vista correcta sin recargar toda la app
            when (selectedScreen) {
                0 -> DashboardScreen()
                1 -> FormScreen()
                2 -> SyncQueueScreen()
            }
        }
    }
}