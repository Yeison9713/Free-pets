package com.example.petspics.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.petspics.data.models.AdoptionForm
import com.example.petspics.data.models.AdoptionPet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionFormScreen(
    pet: AdoptionPet,
    onSubmit: (AdoptionForm) -> Unit,
    onBackClick: () -> Unit
) {
    var applicantName by remember { mutableStateOf("") }
    var applicantEmail by remember { mutableStateOf("") }
    var applicantPhone by remember { mutableStateOf("") }
    var applicantAddress by remember { mutableStateOf("") }
    var applicantCity by remember { mutableStateOf("") }
    var applicantOccupation by remember { mutableStateOf("") }
    var applicantIncome by remember { mutableStateOf("") }
    var hasOtherPets by remember { mutableStateOf(false) }
    var otherPetsDescription by remember { mutableStateOf("") }
    var hasChildren by remember { mutableStateOf(false) }
    var childrenAges by remember { mutableStateOf("") }
    var hasYard by remember { mutableStateOf(false) }
    var yardSize by remember { mutableStateOf("") }
    var hasExperienceWithPets by remember { mutableStateOf(false) }
    var experienceDescription by remember { mutableStateOf("") }
    var hoursAlone by remember { mutableStateOf("") }
    var adoptionReason by remember { mutableStateOf("") }
    var additionalInfo by remember { mutableStateOf("") }
    
    val scrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Adopción") },
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
                .padding(16.dp)
        ) {
            Text(
                text = "Solicitud de Adopción para ${pet.name}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Por favor, completa el siguiente formulario para iniciar el proceso de adopción. Toda la información proporcionada será revisada por el refugio para asegurar que ${pet.name} encuentre un hogar adecuado.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            // Datos personales
            FormSectionTitle(text = "Datos Personales")
            
            OutlinedTextField(
                value = applicantName,
                onValueChange = { applicantName = it },
                label = { Text("Nombre completo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            
            OutlinedTextField(
                value = applicantEmail,
                onValueChange = { applicantEmail = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            
            OutlinedTextField(
                value = applicantPhone,
                onValueChange = { applicantPhone = it },
                label = { Text("Teléfono") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )
            
            OutlinedTextField(
                value = applicantAddress,
                onValueChange = { applicantAddress = it },
                label = { Text("Dirección") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            
            OutlinedTextField(
                value = applicantCity,
                onValueChange = { applicantCity = it },
                label = { Text("Ciudad") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            
            OutlinedTextField(
                value = applicantOccupation,
                onValueChange = { applicantOccupation = it },
                label = { Text("Ocupación") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            
            OutlinedTextField(
                value = applicantIncome,
                onValueChange = { applicantIncome = it },
                label = { Text("Ingresos mensuales (COP)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            
            // Información sobre el hogar
            FormSectionTitle(text = "Información sobre tu Hogar")
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasOtherPets,
                    onCheckedChange = { hasOtherPets = it }
                )
                
                Text(
                    text = "¿Tienes otras mascotas?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            if (hasOtherPets) {
                OutlinedTextField(
                    value = otherPetsDescription,
                    onValueChange = { otherPetsDescription = it },
                    label = { Text("Describe tus mascotas actuales") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    minLines = 2
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasChildren,
                    onCheckedChange = { hasChildren = it }
                )
                
                Text(
                    text = "¿Tienes niños en casa?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            if (hasChildren) {
                OutlinedTextField(
                    value = childrenAges,
                    onValueChange = { childrenAges = it },
                    label = { Text("Edades de los niños") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasYard,
                    onCheckedChange = { hasYard = it }
                )
                
                Text(
                    text = "¿Tienes patio o jardín?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            if (hasYard) {
                OutlinedTextField(
                    value = yardSize,
                    onValueChange = { yardSize = it },
                    label = { Text("Tamaño del patio/jardín") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true
                )
            }
            
            // Experiencia con mascotas
            FormSectionTitle(text = "Experiencia con Mascotas")
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasExperienceWithPets,
                    onCheckedChange = { hasExperienceWithPets = it }
                )
                
                Text(
                    text = "¿Has tenido mascotas antes?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            if (hasExperienceWithPets) {
                OutlinedTextField(
                    value = experienceDescription,
                    onValueChange = { experienceDescription = it },
                    label = { Text("Describe tu experiencia con mascotas") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    minLines = 2
                )
            }
            
            OutlinedTextField(
                value = hoursAlone,
                onValueChange = { hoursAlone = it },
                label = { Text("¿Cuántas horas al día estaría la mascota sola?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            
            // Razones para adoptar
            FormSectionTitle(text = "Razones para Adoptar")
            
            OutlinedTextField(
                value = adoptionReason,
                onValueChange = { adoptionReason = it },
                label = { Text("¿Por qué quieres adoptar a ${pet.name}?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                minLines = 3
            )
            
            OutlinedTextField(
                value = additionalInfo,
                onValueChange = { additionalInfo = it },
                label = { Text("Información adicional que quieras compartir") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                minLines = 3
            )
            
            // Botón de envío
            Button(
                onClick = {
                    val form = AdoptionForm(
                        petId = pet.id,
                        applicantName = applicantName,
                        applicantEmail = applicantEmail,
                        applicantPhone = applicantPhone,
                        applicantAddress = applicantAddress,
                        applicantCity = applicantCity,
                        applicantOccupation = applicantOccupation,
                        applicantIncome = applicantIncome,
                        hasOtherPets = hasOtherPets,
                        otherPetsDescription = otherPetsDescription,
                        hasChildren = hasChildren,
                        childrenAges = childrenAges,
                        hasYard = hasYard,
                        yardSize = yardSize,
                        hasExperienceWithPets = hasExperienceWithPets,
                        experienceDescription = experienceDescription,
                        hoursAlone = hoursAlone,
                        adoptionReason = adoptionReason,
                        additionalInfo = additionalInfo
                    )
                    onSubmit(form)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Enviar Solicitud")
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FormSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 16.dp)
    )
} 