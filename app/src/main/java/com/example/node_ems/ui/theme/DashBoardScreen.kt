package com.example.node_ems.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Estado: En Base (Activo)",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Folios Asignados", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Una lista simulada de incidentes
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(3) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Folio #\${4090 + index}", fontWeight = FontWeight.Bold)
                        Text(text = "Ubicación: Carretera Federal \${85 + index}")
                        Text(text = "Estado: Pendiente de reporte", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}