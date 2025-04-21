package co.poligran.viralpaws.ui.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import co.poligran.viralpaws.data.models.*
import co.poligran.viralpaws.data.SampleData
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionScreen(
    onPetClick: (String) -> Unit = {}
) {
    var selectedPetType by remember { mutableStateOf<PetType?>(null) }
    var selectedAge by remember { mutableStateOf<PetAge?>(null) }
    var selectedSize by remember { mutableStateOf<PetSize?>(null) }
    var selectedGender by remember { mutableStateOf<PetGender?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mascotas en Adopción",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        FilterSection(
            selectedPetType = selectedPetType,
            selectedAge = selectedAge,
            selectedSize = selectedSize,
            selectedGender = selectedGender,
            onPetTypeSelected = { selectedPetType = it },
            onAgeSelected = { selectedAge = it },
            onSizeSelected = { selectedSize = it },
            onGenderSelected = { selectedGender = it }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val filteredPets = SampleData.sampleAdoptionPets.filter { pet ->
                (selectedPetType == null || pet.type == selectedPetType) &&
                (selectedAge == null || pet.age == selectedAge) &&
                (selectedSize == null || pet.size == selectedSize) &&
                (selectedGender == null || pet.gender == selectedGender)
            }
            
            items(filteredPets) { pet ->
                AdoptionPetCard(
                    pet = pet,
                    onClick = { onPetClick(pet.id) }
                )
            }
        }
    }
}

@Composable
fun FilterSection(
    selectedPetType: PetType?,
    selectedAge: PetAge?,
    selectedSize: PetSize?,
    selectedGender: PetGender?,
    onPetTypeSelected: (PetType?) -> Unit,
    onAgeSelected: (PetAge?) -> Unit,
    onSizeSelected: (PetSize?) -> Unit,
    onGenderSelected: (PetGender?) -> Unit
) {
    Column {
        // Tipo de mascota
        Text(
            text = "Tipo",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedPetType == null,
                    onClick = { onPetTypeSelected(null) },
                    label = { Text("Todos") }
                )
            }
            
            items(PetType.values()) { type ->
                FilterChip(
                    selected = selectedPetType == type,
                    onClick = { onPetTypeSelected(type) },
                    label = { Text(getPetTypeName(type)) }
                )
            }
        }
        
        // Edad
        Text(
            text = "Edad",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedAge == null,
                    onClick = { onAgeSelected(null) },
                    label = { Text("Todas") }
                )
            }
            
            items(PetAge.values()) { age ->
                FilterChip(
                    selected = selectedAge == age,
                    onClick = { onAgeSelected(age) },
                    label = { Text(getPetAgeName(age)) }
                )
            }
        }
        
        // Tamaño
        Text(
            text = "Tamaño",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedSize == null,
                    onClick = { onSizeSelected(null) },
                    label = { Text("Todos") }
                )
            }
            
            items(PetSize.values()) { size ->
                FilterChip(
                    selected = selectedSize == size,
                    onClick = { onSizeSelected(size) },
                    label = { Text(getPetSizeName(size)) }
                )
            }
        }
        
        // Género
        Text(
            text = "Género",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedGender == null,
                    onClick = { onGenderSelected(null) },
                    label = { Text("Todos") }
                )
            }
            
            items(PetGender.values()) { gender ->
                FilterChip(
                    selected = selectedGender == gender,
                    onClick = { onGenderSelected(gender) },
                    label = { Text(getPetGenderName(gender)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionPetCard(
    pet: AdoptionPet,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleLarge
                )
                
                Text(
                    text = "${pet.breed} • ${pet.age.name} • ${pet.gender.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = pet.location,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = pet.shelterName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                
                if (pet.adoptionFee != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = pet.adoptionFee,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun PetInfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
            
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
} 