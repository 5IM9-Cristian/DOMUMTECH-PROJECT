package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Message(val text: String, val isUser: Boolean, val time: String)

@Composable
fun AlmacenistaChatScreen(navController: NavController) {
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )
    
    var userMessage by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        Message("¡Hola! Soy tu Almacenista Virtual. Puedo ayudarte a gestionar tu inventario, sugerir recetas con lo que tienes disponible, o responder cualquier pregunta sobre tus productos. ¿En qué puedo asistirte?", false, "04:35 p.m.")
    ) }
    
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mainGradient)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Almacenista Virtual",
                                style = TextStyle(fontSize = 20.sp, color = Color.White)
                            )
                            Text(
                                text = "Asistente inteligente",
                                style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🤖", fontSize = 20.sp)
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mainGradient)
                    .padding(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = userMessage,
                        onValueChange = { userMessage = it },
                        placeholder = { Text("Escribe tu mensaje...", color = Color.LightGray) },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .shadow(4.dp, RoundedCornerShape(28.dp)),
                        shape = RoundedCornerShape(28.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(if (userMessage.isNotBlank()) Color.White else Color.White.copy(alpha = 0.4f))
                            .clickable(enabled = userMessage.isNotBlank()) { 
                                messages.add(Message(userMessage, true, "Ahora"))
                                userMessage = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Enviar",
                            tint = if (userMessage.isNotBlank()) Color(0xFFDC7176) else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
                SuggestionsSection(onSuggestionClick = { suggestion ->
                    messages.add(Message(suggestion, true, "Ahora"))
                })
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleColor = if (message.isUser) Color(0xFFFFF9F2) else Color.White
    val shape = if (message.isUser) 
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 6.dp)
    else 
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 6.dp, bottomEnd = 16.dp)

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = alignment) {
        Card(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .shadow(elevation = 4.dp, shape = shape),
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = bubbleColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = message.text,
                    style = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, color = Color(0xFF1A1A1A))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.time,
                    modifier = Modifier.align(Alignment.End),
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFFDE8948).copy(alpha = 0.7f))
                )
            }
        }
    }
}

@Composable
fun SuggestionsSection(onSuggestionClick: (String) -> Unit) {
    Column {
        Text(
            text = "Sugerencias:",
            style = TextStyle(fontSize = 12.sp, color = Color(0xFFDE8948).copy(alpha = 0.8f))
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        val suggestions = listOf(
            "¿Qué puedo cocinar hoy?",
            "¿Qué necesito comprar?",
            "Muestra mi inventario"
        )

        suggestions.forEach { suggestion ->
            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .clickable { onSuggestionClick(suggestion) }
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = suggestion,
                    style = TextStyle(fontSize = 14.sp, color = Color(0xFF1A1A1A))
                )
            }
        }
    }
}
