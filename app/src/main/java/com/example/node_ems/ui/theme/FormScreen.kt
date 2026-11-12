package com.example.node_ems.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormScreen() {
    val context = LocalContext.current

    // Variables de estado que guardan lo que se escribe
    var patientName by remember { mutableStateOf("") }
    var bloodPressure by remember { mutableStateOf("") }
    var medicalNotes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Captura de ePCR",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Offline Info",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Modo Offline Activo. Los datos se guardarán localmente hasta sincronizar.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        OutlinedTextField(
            value = patientName,
            onValueChange = { patientName = it },
            label = { Text("Nombre del Paciente") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Icono persona")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bloodPressure,
            onValueChange = { bloodPressure = it },
            label = { Text("Presión Arterial (ej. 120/80)") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Icono presión")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = medicalNotes,
            onValueChange = { medicalNotes = it },
            label = { Text("Notas de atención o diagnóstico") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Icono notas")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                // LÓGICA DEL BOTÓN
                if (patientName.isNotBlank() || bloodPressure.isNotBlank() || medicalNotes.isNotBlank()) {
                    Toast.makeText(context, "Reporte guardado localmente", Toast.LENGTH_SHORT).show()
                    patientName = ""
                    bloodPressure = ""
                    medicalNotes = ""
                } else {

                    Toast.makeText(context, "Por favor llena al menos un dato", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Guardar Reporte Local", fontSize = 16.sp)
        }
    }
}