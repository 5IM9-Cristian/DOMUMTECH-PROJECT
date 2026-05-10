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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MiembrosPlanScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )
    
    var emailInvitation by remember { mutableStateOf("") }
    var isInviting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient)
                .padding(24.dp)
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
                        text = "Miembros del Plan",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                    Text(
                        text = "Gestiona tu grupo de compras",
                        style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Summary Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F2), RoundedCornerShape(16.dp))
                    .border(1.5.dp, Color(0xFFE89E5B).copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE89E5B).copy(alpha = 0.8f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "4", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Miembros activos",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                        )
                        Text(
                            text = "Límite: 5 miembros en plan familiar",
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (!isInviting) {
                Button(
                    onClick = { isInviting = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(mainGradient),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Invitar Nuevo Miembro", color = Color.White, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "Enviar Invitación",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Correo electrónico",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = emailInvitation,
                            onValueChange = { emailInvitation = it },
                            placeholder = { Text("invitado@email.com", color = Color.LightGray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.LightGray) },
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { isInviting = false; emailInvitation = "" }) {
                                Text("Cancelar", color = Color(0xFF1A1A1A))
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                onClick = { isInviting = false; emailInvitation = "" },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC7176).copy(alpha = 0.8f)),
                                shape = RoundedCornerShape(14.dp),
                                enabled = emailInvitation.isNotBlank()
                            ) {
                                Text("Enviar Invitación", color = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Current Members
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Miembros Actuales",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    MemberListItem(name = "María González", email = "maria@email.com", role = "Propietario", emoji = "👩", showDelete = false)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    MemberListItem(name = "Juan Pérez", email = "juan@email.com", role = "Admin", emoji = "👨")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    MemberListItem(name = "Ana García", email = "ana@email.com", role = "Miembro", emoji = "👧")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    MemberListItem(name = "Pedro López", email = "pedro@email.com", role = "Miembro", emoji = "👦")
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MemberListItem(name: String, email: String, role: String, emoji: String, showDelete: Boolean = true) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                Text(text = email, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFDC7176).copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(text = role, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Medium)
            }
            if (showDelete) {
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color(0xFFDC7176),
                    modifier = Modifier.size(20.dp).clickable { }
                )
            }
        }
    }
}
