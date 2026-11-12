package com.example.node_ems.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.CloudUpload,
            contentDescription = "Sincronizar",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lote ePCR Pendiente",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isSyncing) "Sincronizando con el servidor..." else "3 reportes listos para subir al servidor.",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

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
            enabled = !isSyncing,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = if (isSyncing) "Sincronizando..." else "Sincronizar Lote ePCR", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Desliza la barra de estado hacia abajo al presionar el botón para ver el progreso de carga",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}