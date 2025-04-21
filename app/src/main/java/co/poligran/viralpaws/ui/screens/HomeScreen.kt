package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import co.poligran.viralpaws.data.models.PetPost
import co.poligran.viralpaws.data.models.Comment
import co.poligran.viralpaws.data.SampleData
import co.poligran.viralpaws.ui.components.ImageLoader
import co.poligran.viralpaws.ui.components.YTShortView

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
    var showShortDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            ImageLoader(
                imageUrl = post.imageUrl,
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable { showShortDialog = true },
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
    if (showShortDialog) {
        Dialog(onDismissRequest = { showShortDialog = false }) {
            YTShortView(
                url = post.youtubeShortUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Ajusta la altura según necesites
            )
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