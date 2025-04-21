package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import co.poligran.viralpaws.data.models.PetPost
import co.poligran.viralpaws.data.SampleData
import co.poligran.viralpaws.data.models.Comment
import co.poligran.viralpaws.ui.components.ImageLoader

@Composable
fun ExploreScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    
    val categories = listOf("Todos", "Perros", "Gatos", "Aves", "Otros")
    
    val filteredPosts = SampleData.samplePosts.filter { post ->
        val matchesSearch = searchQuery.isEmpty() || 
            post.petName.contains(searchQuery, ignoreCase = true) ||
            post.ownerName.contains(searchQuery, ignoreCase = true) ||
            post.description.contains(searchQuery, ignoreCase = true)
            
        val matchesCategory = selectedCategory == "Todos" ||
            (selectedCategory == "Perros" && post.petName.contains("perro", ignoreCase = true)) ||
            (selectedCategory == "Gatos" && post.petName.contains("gato", ignoreCase = true)) ||
            (selectedCategory == "Aves" && post.petName.contains("ave", ignoreCase = true)) ||
            (selectedCategory == "Otros" && !post.petName.contains("perro", ignoreCase = true) &&
                !post.petName.contains("gato", ignoreCase = true) &&
                !post.petName.contains("ave", ignoreCase = true))
            
        matchesSearch && matchesCategory
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explorar",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Buscar mascotas...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) }
                )
            }
        }
        
        Text(
            text = "${filteredPosts.size} resultados",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (filteredPosts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No se encontraron mascotas",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredPosts) { post ->
                    ExplorePetCard(post = post)
                }
            }
        }
    }
}

@Composable
fun ExplorePetCard(post: PetPost) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            ImageLoader(
                imageUrl = post.imageUrl,
                contentDescription = "Imagen de ${post.petName}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = post.petName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "por ${post.ownerName}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// Sample data
private val samplePosts = listOf(
    PetPost(
        id = "1",
        petName = "Luna",
        animal = "Perro",
        ownerName = "María",
        description = "Mi Beagle posando para la foto",
        imageUrl = "https://www.superpet.ec/wp-content/uploads/2022/02/beagle-6.jpg",
        likes = 42,
        comments = listOf(
            Comment(
                id = "1",
                userName = "Carlos",
                text = "¡Qué lindaaa!",
                timestamp = "2h"
            )
        ),
        youtubeShortUrl = "https://www.youtube.com/shorts/iY4bwiMTCns"
    ),
    PetPost(
        id = "2",
        petName = "Michi",
        animal = "Gato",
        ownerName = "Carlos",
        description = "Mi gato siamés tomando el sol",
        imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
        likes = 28,
        comments = listOf(
            Comment(
                id = "2",
                userName = "Ana",
                text = "Hermoso gato",
                timestamp = "1h"
            )
        ),
        youtubeShortUrl = "https://www.youtube.com/shorts/zCk5cHkrCpc"
    ),
    PetPost(
        id = "3",
        petName = "Rocky",
        animal = "Perro",
        ownerName = "Ana",
        description = "Mirando para pasar la calle",
        imageUrl = "https://i.pinimg.com/originals/f3/7c/bd/f37cbdec24b3642732ff4d9aebaadb14.jpg",
        likes = 35,
        comments = listOf(
            Comment(
                id = "3",
                userName = "María",
                text = "¡Qué inteligente!",
                timestamp = "30m"
            )
        ),
        youtubeShortUrl = "https://www.youtube.com/shorts/5XduUERse8A"
    )
) 