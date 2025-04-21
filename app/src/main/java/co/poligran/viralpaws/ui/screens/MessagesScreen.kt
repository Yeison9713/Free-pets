package co.poligran.viralpaws.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
data class Chat(
    val id: String,
    val userName: String,
    val petName: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int = 0,
    val messages: List<Message> = emptyList()
)

data class Message(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val text: String,
    val timestamp: String,
    val isRead: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen() {
    var chats by remember { mutableStateOf(sampleChats) }
    var selectedChat by remember { mutableStateOf<Chat?>(null) }
    
    if (selectedChat != null) {
        ChatScreen(
            chat = selectedChat!!,
            onBackClick = { selectedChat = null },
            onSendMessage = { text ->
                val newMessage = Message(
                    id = System.currentTimeMillis().toString(),
                    senderId = "currentUser",
                    receiverId = selectedChat!!.id,
                    text = text,
                    timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                )
                
                chats = chats.map { chat ->
                    if (chat.id == selectedChat!!.id) {
                        chat.copy(
                            messages = chat.messages + newMessage,
                            lastMessage = text,
                            timestamp = newMessage.timestamp
                        )
                    } else {
                        chat
                    }
                }
                
                selectedChat = chats.find { it.id == selectedChat!!.id }
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Mensajes",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chats) { chat ->
                    ChatItem(
                        chat = chat,
                        onClick = { selectedChat = chat }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatItem(
    chat: Chat,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = chat.userName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Mascota: ${chat.petName}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = chat.lastMessage,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = chat.timestamp,
                    style = MaterialTheme.typography.bodySmall
                )
                if (chat.unreadCount > 0) {
                    Badge(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(chat.unreadCount.toString())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chat: Chat,
    onBackClick: () -> Unit,
    onSendMessage: (String) -> Unit
) {
    var messageText by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Chat header
        TopAppBar(
            title = {
                Column {
                    Text(text = chat.userName)
                    Text(
                        text = "Mascota: ${chat.petName}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            }
        )
        
        // Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(chat.messages) { message ->
                MessageItem(message = message)
            }
        }
        
        // Message input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Escribe un mensaje...") },
                maxLines = 3
            )
            
            IconButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        onSendMessage(messageText)
                        messageText = ""
                    }
                },
                enabled = messageText.isNotBlank()
            ) {
                Icon(Icons.Default.Send, contentDescription = "Enviar")
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    val isCurrentUser = message.senderId == "currentUser"
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isCurrentUser) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.widthIn(max = 340.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

// Sample data
private val sampleChats = listOf(
    Chat(
        id = "1",
        userName = "María",
        petName = "Luna (perro)",
        lastMessage = "¡Qué lindo perro!",
        timestamp = "2h",
        unreadCount = 1,
        messages = listOf(
            Message(
                id = "1",
                senderId = "1",
                receiverId = "currentUser",
                text = "¡Hola! Vi tu foto de Luna en el parque",
                timestamp = "14:30"
            ),
            Message(
                id = "2",
                senderId = "currentUser",
                receiverId = "1",
                text = "¡Hola! Sí, es mi labrador",
                timestamp = "14:32"
            ),
            Message(
                id = "3",
                senderId = "1",
                receiverId = "currentUser",
                text = "¡Qué lindo perro!",
                timestamp = "14:35"
            )
        )
    ),
    Chat(
        id = "2",
        userName = "Carlos",
        petName = "Michi (gato)",
        lastMessage = "¿Qué raza es?",
        timestamp = "1h",
        messages = listOf(
            Message(
                id = "4",
                senderId = "2",
                receiverId = "currentUser",
                text = "Hola, vi tu foto de Michi",
                timestamp = "13:45"
            ),
            Message(
                id = "5",
                senderId = "currentUser",
                receiverId = "2",
                text = "¡Hola! Sí, es mi gato siamés",
                timestamp = "13:47"
            ),
            Message(
                id = "6",
                senderId = "2",
                receiverId = "currentUser",
                text = "¿Qué raza es?",
                timestamp = "13:50"
            )
        )
    ),
    Chat(
        id = "3",
        userName = "Ana",
        petName = "Rocky (perro)",
        lastMessage = "¡Qué feliz se ve!",
        timestamp = "30m",
        messages = listOf(
            Message(
                id = "7",
                senderId = "3",
                receiverId = "currentUser",
                text = "¡Hola! Vi la foto de Rocky en la playa",
                timestamp = "12:15"
            ),
            Message(
                id = "8",
                senderId = "currentUser",
                receiverId = "3",
                text = "¡Hola! Sí, le encanta jugar en la arena",
                timestamp = "12:17"
            ),
            Message(
                id = "9",
                senderId = "3",
                receiverId = "currentUser",
                text = "¡Qué feliz se ve!",
                timestamp = "12:20"
            )
        )
    )
) 