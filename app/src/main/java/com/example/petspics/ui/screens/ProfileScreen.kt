package com.example.petspics.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petspics.R
import com.example.petspics.data.models.Pet
import com.example.petspics.ui.components.AddPetForm
import com.example.petspics.ui.components.ImageLoader
import com.example.petspics.ui.components.PhotoGallery

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showAddPetDialog by remember { mutableStateOf(false) }
    var userPets by remember { mutableStateOf(listOf<Pet>()) }
    var selectedPet by remember { mutableStateOf<Pet?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Profile Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Profile Background
            ImageLoader(
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba",
                contentDescription = "Profile Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // Profile Picture
            ImageLoader(
                imageUrl = "https://images.unsplash.com/photo-1535930891776-0c2dfb7fda1a",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )
        }
        
        // User Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "John Doe",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "@johndoe",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Amante de los animales y fotógrafo aficionado",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "42",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Posts",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "1.2K",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Seguidores",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "890",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Siguiendo",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // My Pets Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis Mascotas",
                    style = MaterialTheme.typography.titleLarge
                )
                
                IconButton(onClick = { showAddPetDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar mascota"
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // User's Pets
            if (userPets.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No tienes mascotas registradas",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    userPets.forEach { pet ->
                        PetCard(
                            pet = pet,
                            onClick = { selectedPet = pet }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Logout Button
            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(
                    Icons.Default.Logout,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Cerrar Sesión")
            }
        }
    }
    
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Cerrar Sesión") },
            text = { Text("¿Estás seguro de que quieres cerrar sesión?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }
                ) {
                    Text("Sí, cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    
    if (showAddPetDialog) {
        AlertDialog(
            onDismissRequest = { showAddPetDialog = false },
            title = { Text("Agregar nueva mascota") },
            text = {
                AddPetForm(
                    onSubmit = { pet ->
                        userPets = userPets + pet
                        showAddPetDialog = false
                    },
                    onCancel = { showAddPetDialog = false }
                )
            },
            confirmButton = { },
            dismissButton = { }
        )
    }
    
    if (selectedPet != null) {
        AlertDialog(
            onDismissRequest = { selectedPet = null },
            title = { Text(selectedPet!!.name) },
            text = {
                Column {
                    ImageLoader(
                        imageUrl = selectedPet!!.photo,
                        contentDescription = "${selectedPet!!.name}'s main picture",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Galería de fotos",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    PhotoGallery(
                        photos = selectedPet!!.photos.map { android.net.Uri.parse(it) },
                        onAddPhoto = { /* Implementar agregar foto */ },
                        onDeletePhoto = { /* Implementar eliminar foto */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedPet = null }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun PetCard(
    pet: Pet,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            ImageLoader(
                imageUrl = pet.photo,
                contentDescription = "${pet.name}'s picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = pet.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
} 