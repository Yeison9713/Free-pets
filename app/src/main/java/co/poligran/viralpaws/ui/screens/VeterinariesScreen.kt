package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import co.poligran.viralpaws.data.SampleData
import co.poligran.viralpaws.ui.components.ImageLoader
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinariesScreen(
    onVeterinaryClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf<String?>(null) }
    var showCityFilter by remember { mutableStateOf(false) }
    
    val cities = remember {
        SampleData.sampleVeterinaries.map { it.city }.distinct().sorted()
    }
    
    val filteredVeterinaries = remember(searchQuery, selectedCity) {
        SampleData.sampleVeterinaries.filter { veterinary ->
            val matchesSearch = veterinary.name.contains(searchQuery, ignoreCase = true) ||
                    veterinary.city.contains(searchQuery, ignoreCase = true) ||
                    veterinary.description.contains(searchQuery, ignoreCase = true)
            val matchesCity = selectedCity == null || veterinary.city == selectedCity
            matchesSearch && matchesCity
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Buscar veterinarias...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar búsqueda")
                    }
                }
            },
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // City filter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filtrar por ciudad:",
                style = MaterialTheme.typography.titleMedium
            )
            
            TextButton(onClick = { showCityFilter = true }) {
                Text(selectedCity ?: "Todas las ciudades")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Veterinaries list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredVeterinaries) { veterinary ->
                VeterinaryCard(
                    veterinary = veterinary,
                    onClick = { onVeterinaryClick(veterinary.id) }
                )
            }
        }
    }
    
    if (showCityFilter) {
        AlertDialog(
            onDismissRequest = { showCityFilter = false },
            title = { Text("Seleccionar ciudad") },
            text = {
                LazyColumn {
                    item {
                        TextButton(
                            onClick = {
                                selectedCity = null
                                showCityFilter = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Todas las ciudades")
                        }
                    }
                    
                    items(cities) { city ->
                        TextButton(
                            onClick = {
                                selectedCity = city
                                showCityFilter = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(city)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCityFilter = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinaryCard(
    veterinary: co.poligran.viralpaws.data.models.Veterinary,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                ImageLoader(
                    imageUrl = veterinary.imageUrl,
                    contentDescription = veterinary.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                // Emergency badge
                if (veterinary.isEmergency) {
                    Surface(
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = "EMERGENCIA",
                            color = MaterialTheme.colorScheme.onError,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                
                // 24 hours badge
                if (veterinary.isOpen24Hours) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd)
                            .padding(top = if (veterinary.isEmergency) 48.dp else 0.dp)
                    ) {
                        Text(
                            text = "24 HORAS",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = veterinary.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = String.format("%.1f", veterinary.rating),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${veterinary.city}, ${veterinary.address}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = veterinary.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                @OptIn(ExperimentalLayoutApi::class)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    veterinary.services.take(3).forEach { service ->
                        AssistChip(
                            onClick = { },
                            label = { Text(service) }
                        )
                    }
                    
                    if (veterinary.services.size > 3) {
                        AssistChip(
                            onClick = { },
                            label = { Text("+${veterinary.services.size - 3} más") }
                        )
                    }
                }
            }
        }
    }
} 