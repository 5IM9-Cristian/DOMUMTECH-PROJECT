package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlmacenistaChatScreen() {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )

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
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Almacenista Virtual",
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
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
                        value = "",
                        onValueChange = {},
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
                        )
                    )
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Enviar",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            // Mensaje de Bienvenida del Bot
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 6.dp, bottomEnd = 16.dp))
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 6.dp, bottomEnd = 16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "¡Hola! Soy tu Almacenista Virtual. Puedo ayudarte a gestionar tu inventario, sugerir recetas con lo que tienes disponible, o responder cualquier pregunta sobre tus productos. ¿En qué puedo asistirte?",
                        style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xFF1A1A1A))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "04:35 p.m.",
                        modifier = Modifier.align(Alignment.End),
                        style = TextStyle(fontSize = 12.sp, color = Color(0xFFDE8948).copy(alpha = 0.7f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Sugerencias Section
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
                        .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp))
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .clickable { }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = suggestion,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun AlmacenistaChatScreenPreview() {
    AlmacenistaChatScreen()
}
