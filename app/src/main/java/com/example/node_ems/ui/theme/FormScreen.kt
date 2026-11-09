package com.example.node_ems.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FormScreen() {
    var paciente by remember { mutableStateOf("") }
    var presion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Captura de ePCR", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Llene los datos del paciente (Offline)", color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = paciente,
            onValueChange = { paciente = it },
            label = { Text("Nombre del Paciente") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = presion,
            onValueChange = { presion = it },
            label = { Text("Presión Arterial (ej. 120/80)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                paciente = ""
                presion = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Reporte Local")
        }
    }
}