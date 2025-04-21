package co.poligran.viralpaws.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import co.poligran.viralpaws.data.models.AdoptionPet
import co.poligran.viralpaws.data.models.getPetAgeName
import co.poligran.viralpaws.data.models.getPetGenderName
import co.poligran.viralpaws.data.models.getPetSizeName
import co.poligran.viralpaws.data.models.getPetTypeName
import co.poligran.viralpaws.ui.components.AdoptionProcessStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailsScreen(
    pet: AdoptionPet,
    onAdoptClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de ${pet.name}") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Imagen de la mascota
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Información básica
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${getPetTypeName(pet.type)} - ${pet.breed}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                
                // Características
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PetInfoChip(
                        icon = Icons.Default.Pets,
                        text = getPetAgeName(pet.age)
                    )
                    
                    PetInfoChip(
                        icon = Icons.Default.Pets,
                        text = getPetSizeName(pet.size)
                    )
                    
                    PetInfoChip(
                        icon = Icons.Default.Pets,
                        text = getPetGenderName(pet.gender)
                    )
                }
                
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                
                // Descripción
                Text(
                    text = "Acerca de ${pet.name}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = pet.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Características adicionales
                Text(
                    text = "Características",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                PetFeatureRow(
                    icon = Icons.Default.Vaccines,
                    text = "Vacunado",
                    isActive = pet.isVaccinated
                )
                
                PetFeatureRow(
                    icon = Icons.Default.Pets,
                    text = "Esterilizado",
                    isActive = pet.isNeutered
                )
                
                PetFeatureRow(
                    icon = Icons.Default.LocationOn,
                    text = "Microchip",
                    isActive = pet.isMicrochipped
                )
                
                PetFeatureRow(
                    icon = Icons.Default.Home,
                    text = "Entrenado para casa",
                    isActive = pet.isHouseTrained
                )
                
                PetFeatureRow(
                    icon = Icons.Default.ChildCare,
                    text = "Bueno con niños",
                    isActive = pet.isGoodWithKids
                )
                
                PetFeatureRow(
                    icon = Icons.Default.Pets,
                    text = "Bueno con perros",
                    isActive = pet.isGoodWithDogs
                )
                
                PetFeatureRow(
                    icon = Icons.Default.Pets,
                    text = "Bueno con gatos",
                    isActive = pet.isGoodWithCats
                )
                
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                
                // Información del refugio
                Text(
                    text = "Refugio",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = pet.shelterImageUrl,
                        contentDescription = pet.shelterName,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pet.shelterName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Text(
                            text = pet.location,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        
                        RatingBar(rating = pet.shelterRating, reviews = pet.shelterReviews)
                    }
                }
                
                if (pet.adoptionFee != null) {
                    Text(
                        text = pet.adoptionFee,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                
                // Proceso de adopción
                Text(
                    text = "Proceso de Adopción",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                AdoptionProcessStep(
                    number = 1,
                    title = "Solicitud",
                    description = "Completa el formulario de adopción con tus datos personales y responde preguntas sobre tu estilo de vida."
                )
                
                AdoptionProcessStep(
                    number = 2,
                    title = "Revisión",
                    description = "El refugio revisará tu solicitud y verificará que seas un buen candidato para adoptar a ${pet.name}."
                )
                
                AdoptionProcessStep(
                    number = 3,
                    title = "Entrevista",
                    description = "Si tu solicitud es aprobada, se programará una entrevista para conocerte mejor y responder tus preguntas."
                )
                
                AdoptionProcessStep(
                    number = 4,
                    title = "Visita",
                    description = "Podrás visitar a ${pet.name} en el refugio para interactuar y asegurarte de que sea la mascota adecuada para ti."
                )
                
                AdoptionProcessStep(
                    number = 5,
                    title = "Adopción",
                    description = "Si todo va bien, firmarás el contrato de adopción y podrás llevarte a ${pet.name} a su nuevo hogar."
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Botón de adopción
                Button(
                    onClick = onAdoptClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Iniciar Proceso de Adopción")
                }
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun PetFeatureRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    isActive: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.padding(end = 16.dp)
        )
        
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isActive) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
    }
} 