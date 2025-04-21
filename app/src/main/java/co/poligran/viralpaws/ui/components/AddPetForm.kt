package co.poligran.viralpaws.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.poligran.viralpaws.data.models.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetForm(
    onSubmit: (Pet) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var additionalPhotos by remember { mutableStateOf(listOf<Uri>()) }
    
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Agregar nueva mascota",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Foto principal
        Text(
            text = "Foto principal",
            style = MaterialTheme.typography.titleMedium
        )
        
        if (selectedImageUri != null) {
            ImagePreview(
                imageUri = selectedImageUri!!,
                onEdit = { /* Implementar edición */ },
                onDelete = { selectedImageUri = null },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        } else {
            ImagePicker(
                onImageSelected = { uri -> selectedImageUri = uri },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        
        // Fotos adicionales
        Text(
            text = "Fotos adicionales",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        
        PhotoGallery(
            photos = additionalPhotos,
            onAddPhoto = {
                // Aquí se implementará la selección de fotos adicionales
            },
            onDeletePhoto = { photo ->
                additionalPhotos = additionalPhotos - photo
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        // Campos de texto
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de la mascota") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Tipo (Perro, Gato, etc.)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        OutlinedTextField(
            value = breed,
            onValueChange = { breed = it },
            label = { Text("Raza") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad (años)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }
            
            Button(
                onClick = {
                    if (name.isNotBlank() && type.isNotBlank() && selectedImageUri != null) {
                        val pet = Pet(
                            id = System.currentTimeMillis().toString(),
                            name = name,
                            type = type,
                            breed = breed,
                            age = age.toIntOrNull() ?: 0,
                            photo = selectedImageUri.toString(),
                            photos = additionalPhotos.map { it.toString() }
                        )
                        onSubmit(pet)
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = name.isNotBlank() && type.isNotBlank() && selectedImageUri != null
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Agregar")
            }
        }
    }
} 