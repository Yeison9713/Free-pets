package com.example.petspics.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.petspics.data.models.*
import com.example.petspics.data.SampleData
import com.example.petspics.ui.components.ImageLoader
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Salud y Comportamiento",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Consejos") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Registros") }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("Comportamiento") }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        when (selectedTab) {
            0 -> HealthTipsSection()
            1 -> HealthRecordsSection()
            2 -> BehaviorSection()
        }
    }
}

@Composable
fun HealthTipsSection() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(SampleData.sampleHealthTips) { tip ->
            HealthTipCard(tip = tip)
        }
    }
}

@Composable
fun HealthTipCard(tip: HealthTip) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            ImageLoader(
                imageUrl = tip.imageUrl,
                contentDescription = tip.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleLarge
                )
                
                Text(
                    text = tip.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                
                AssistChip(
                    onClick = { },
                    label = { Text(getTipCategoryName(tip.category)) },
                    leadingIcon = {
                        Icon(
                            imageVector = getTipCategoryIcon(tip.category),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun HealthRecordsSection() {
    var showAddDialog by remember { mutableStateOf(false) }
    
    Column {
        Button(
            onClick = { showAddDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Agregar Registro")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Aquí irían los registros de salud
        Text(
            text = "No hay registros de salud",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
    
    if (showAddDialog) {
        AddHealthRecordDialog(
            onDismiss = { showAddDialog = false },
            onSave = { /* TODO: Implementar guardado */ }
        )
    }
}

@Composable
fun BehaviorSection() {
    var showAddDialog by remember { mutableStateOf(false) }
    
    Column {
        Button(
            onClick = { showAddDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Agregar Comportamiento")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Aquí irían los registros de comportamiento
        Text(
            text = "No hay registros de comportamiento",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
    
    if (showAddDialog) {
        AddBehaviorDialog(
            onDismiss = { showAddDialog = false },
            onSave = { /* TODO: Implementar guardado */ }
        )
    }
}

@Composable
fun AddHealthRecordDialog(
    onDismiss: () -> Unit,
    onSave: (HealthRecord) -> Unit
) {
    var selectedType by remember { mutableStateOf(RecordType.VACCINE) }
    var description by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Registro de Salud") },
        text = {
            Column {
                // Tipo de registro
                Text(
                    text = "Tipo de registro",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(RecordType.values()) { type ->
                        FilterChip(
                            selected = selectedType == type,
                            onClick = { selectedType = type },
                            label = { Text(getRecordTypeName(type)) }
                        )
                    }
                }
                
                // Descripción
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val record = HealthRecord(
                        id = UUID.randomUUID().toString(),
                        petId = "1", // TODO: Obtener ID de la mascota actual
                        type = selectedType,
                        date = Date(),
                        description = description
                    )
                    onSave(record)
                    onDismiss()
                },
                enabled = description.isNotBlank()
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
fun AddBehaviorDialog(
    onDismiss: () -> Unit,
    onSave: (PetBehavior) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(BehaviorCategory.TRAINING) }
    var description by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Comportamiento") },
        text = {
            Column {
                // Categoría
                Text(
                    text = "Categoría",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(BehaviorCategory.values()) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(getBehaviorCategoryName(category)) }
                        )
                    }
                }
                
                // Descripción
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Notas
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notas adicionales") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val behavior = PetBehavior(
                        id = UUID.randomUUID().toString(),
                        petId = "1", // TODO: Obtener ID de la mascota actual
                        category = selectedCategory,
                        description = description,
                        date = Date(),
                        notes = notes
                    )
                    onSave(behavior)
                    onDismiss()
                },
                enabled = description.isNotBlank()
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

fun getTipCategoryName(category: TipCategory): String {
    return when (category) {
        TipCategory.NUTRITION -> "Nutrición"
        TipCategory.EXERCISE -> "Ejercicio"
        TipCategory.HYGIENE -> "Higiene"
        TipCategory.PREVENTION -> "Prevención"
        TipCategory.BEHAVIOR -> "Comportamiento"
        TipCategory.FIRST_AID -> "Primeros Auxilios"
    }
}

fun getTipCategoryIcon(category: TipCategory) = when (category) {
    TipCategory.NUTRITION -> Icons.Default.Restaurant
    TipCategory.EXERCISE -> Icons.Default.DirectionsRun
    TipCategory.HYGIENE -> Icons.Default.CleanHands
    TipCategory.PREVENTION -> Icons.Default.HealthAndSafety
    TipCategory.BEHAVIOR -> Icons.Default.Psychology
    TipCategory.FIRST_AID -> Icons.Default.LocalHospital
}

fun getRecordTypeName(type: RecordType): String {
    return when (type) {
        RecordType.VACCINE -> "Vacuna"
        RecordType.MEDICAL_CHECKUP -> "Control Médico"
        RecordType.BEHAVIOR_NOTE -> "Nota de Comportamiento"
        RecordType.MEDICATION -> "Medicación"
        RecordType.SURGERY -> "Cirugía"
        RecordType.OTHER -> "Otro"
    }
}

fun getBehaviorCategoryName(category: BehaviorCategory): String {
    return when (category) {
        BehaviorCategory.TRAINING -> "Entrenamiento"
        BehaviorCategory.SOCIALIZATION -> "Socialización"
        BehaviorCategory.AGGRESSION -> "Agresión"
        BehaviorCategory.ANXIETY -> "Ansiedad"
        BehaviorCategory.OBEDIENCE -> "Obediencia"
        BehaviorCategory.OTHER -> "Otro"
    }
} 