package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.poligran.viralpaws.data.models.PetPost
import co.poligran.viralpaws.data.models.Comment
import co.poligran.viralpaws.data.SampleData
import co.poligran.viralpaws.ui.components.ImageLoader

@Composable
fun HomeScreen() {
    var posts by remember { mutableStateOf(SampleData.samplePosts) }
    var selectedPost by remember { mutableStateOf<PetPost?>(null) }
    var showCommentDialog by remember { mutableStateOf(false) }
    var showContactDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Inicio",
                style = MaterialTheme.typography.headlineLarge
            )
            
            IconButton(onClick = { showContactDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Contactar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posts) { post ->
                PetPostCard(
                    post = post,
                    onLikeClick = {
                        posts = posts.map { 
                            if (it.id == post.id) it.copy(likes = it.likes + 1) else it 
                        }
                    },
                    onCommentClick = {
                        selectedPost = post
                        showCommentDialog = true
                    }
                )
            }
        }
    }
    
    if (showContactDialog) {
        AlertDialog(
            onDismissRequest = { showContactDialog = false },
            title = { Text("Contáctanos") },
            text = {
                Column {
                    Text("LAURA ALEJANDRA LOPEZ FORERO")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("JAVIER LOPEZ SAAVEDRA")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("YEISSON STIVEN OTALORA MORENO")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("JOSÉ RODRÍGUEZ CORTÉS")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("JAIME ANDRÉS TORRES DUQUE")
                }
            },
            confirmButton = {
                TextButton(onClick = { showContactDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
    
    if (showCommentDialog && selectedPost != null) {
        AlertDialog(
            onDismissRequest = { showCommentDialog = false },
            title = { Text("Comentarios") },
            text = {
                Column {
                    selectedPost!!.comments.forEach { comment ->
                        CommentItem(comment = comment)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCommentDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun PetPostCard(
    post: PetPost,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            ImageLoader(
                imageUrl = post.imageUrl,
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = post.ownerName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Text(
                        text = post.petName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = post.description,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onLikeClick) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Like",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "${post.likes} likes",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onCommentClick) {
                            Icon(
                                imageVector = Icons.Default.Comment,
                                contentDescription = "Comment"
                            )
                        }
                        Text(
                            text = "${post.comments.size} comentarios",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = comment.userName,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = comment.timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = comment.text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Sample data
private val samplePosts = listOf(
    PetPost(
        id = "1",
        petName = "Luna (perro)",
        ownerName = "María",
        description = "Mi labrador disfrutando del parque",
        imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
        likes = 42,
        comments = listOf(
            Comment(
                id = "1",
                userName = "Carlos",
                text = "¡Qué lindo perro!",
                timestamp = "2h"
            )
        )
    ),
    PetPost(
        id = "2",
        petName = "Michi (gato)",
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
        )
    ),
    PetPost(
        id = "3",
        petName = "Rocky (perro)",
        ownerName = "Ana",
        description = "Jugando en la playa",
        imageUrl = "https://images.unsplash.com/photo-1477884213360-7e9d7dcc1e48?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
        likes = 35,
        comments = listOf(
            Comment(
                id = "3",
                userName = "María",
                text = "¡Qué feliz se ve!",
                timestamp = "30m"
            )
        )
    )
) 