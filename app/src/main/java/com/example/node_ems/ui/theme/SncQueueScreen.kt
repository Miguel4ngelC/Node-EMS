package com.example.node_ems.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.node_ems.notifications.NotificationHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SyncQueueScreen() {
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }
    val coroutineScope = rememberCoroutineScope()
    var isSyncing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (!isSyncing) {
                    isSyncing = true
                    coroutineScope.launch {
                        for (i in 1..100) {
                            notificationHelper.showProgress(i)
                            delay(50)
                        }
                        isSyncing = false
                    }
                }
            },
            enabled = !isSyncing
        ) {
            Text(if (isSyncing) "Sincronizando..." else "Sincronizar Lote ePCR")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Desliza la barra de estado hacia abajo al presionar el boton")
    }
}