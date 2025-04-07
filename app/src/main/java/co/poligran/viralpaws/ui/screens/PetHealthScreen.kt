package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.poligran.viralpaws.model.BehaviorRecord
import co.poligran.viralpaws.model.HealthRecord
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun PetHealthScreen(
    modifier: Modifier = Modifier
) {
    var showAddHealthRecord by remember { mutableStateOf(false) }
    var showAddBehaviorRecord by remember { mutableStateOf(false) }
    var newWeight by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sección de Información General
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Salud y comportamiento de las mascotas",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "· Los perros pueden orinar cuando estén excitados o sumisos.",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "· Los gatos pueden mostrar estrés jadeando, ocultándose, pulverizándose, moviendo la cabeza, perdiendo interés en la comida, o acicalándose en exceso o en defecto.",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "· El comportamiento de las mascotas puede estar relacionado con conflictos, como cuando orinan al saludar a las personas.",
                    fontSize = 16.sp
                )
            }
        }

        // Espacio para imágenes
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Espacio para imágenes de mascotas",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Sección de Registros de Salud
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Registros de Salud",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(onClick = { showAddHealthRecord = true }) {
                        Text("Agregar Registro")
                    }
                }
                
                if (showAddHealthRecord) {
                    AddHealthRecordDialog(
                        onDismiss = { showAddHealthRecord = false },
                        onRecordAdded = { record ->
                            // TODO: Implementar la lógica para agregar el registro
                            showAddHealthRecord = false
                        }
                    )
                }
            }
        }

        // Sección de Registros de Comportamiento
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Registros de Comportamiento",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(onClick = { showAddBehaviorRecord = true }) {
                        Text("Agregar Registro")
                    }
                }
                
                if (showAddBehaviorRecord) {
                    AddBehaviorRecordDialog(
                        onDismiss = { showAddBehaviorRecord = false },
                        onRecordAdded = { record ->
                            // TODO: Implementar la lógica para agregar el registro
                            showAddBehaviorRecord = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun AddHealthRecordDialog(
    onDismiss: () -> Unit,
    onRecordAdded: (HealthRecord) -> Unit
) {
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var veterinarian by remember { mutableStateOf("") }
    var nextAppointment by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Registro de Salud") },
        text = {
            Column {
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Tipo de registro") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = veterinarian,
                    onValueChange = { veterinarian = it },
                    label = { Text("Veterinario") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nextAppointment,
                    onValueChange = { nextAppointment = it },
                    label = { Text("Próxima cita (opcional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val record = HealthRecord(
                        id = UUID.randomUUID().toString(),
                        date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                        type = type,
                        description = description,
                        veterinarian = veterinarian,
                        nextAppointment = nextAppointment.takeIf { it.isNotBlank() }
                    )
                    onRecordAdded(record)
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun AddBehaviorRecordDialog(
    onDismiss: () -> Unit,
    onRecordAdded: (BehaviorRecord) -> Unit
) {
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Registro de Comportamiento") },
        text = {
            Column {
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Tipo de registro") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notas adicionales") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val record = BehaviorRecord(
                        id = UUID.randomUUID().toString(),
                        date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                        type = type,
                        description = description,
                        notes = notes
                    )
                    onRecordAdded(record)
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
} 