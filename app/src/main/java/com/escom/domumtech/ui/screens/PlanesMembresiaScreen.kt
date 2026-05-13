package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PlanesMembresiaScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header con Gradiente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .padding(24.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Planes y Membresía",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }

                // Plan Actual Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Plan Actual",
                        style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                    )
                    Text(
                        text = "Plan Familiar",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Plan Premium Highlight Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 15.dp, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(mainGradient)
                        .padding(24.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Plan Familiar Premium",
                                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                                )
                                Text(
                                    text = "Gestión colaborativa completa",
                                    style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Gratis",
                                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            )
                            Text(
                                text = "durante el período beta",
                                modifier = Modifier.padding(start = 4.dp),
                                style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Features List
                        val features = listOf(
                            "Inventario ilimitado",
                            "Hasta 5 miembros familiares",
                            "Almacenista IA ilimitado",
                            "Lista de compras inteligente",
                            "Escaneo OCR de productos"
                        )

                        features.forEach { feature ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(Color.White.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = feature,
                                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Beneficios Section
            SectionHeader(title = "Beneficios del Plan")
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BenefitItem(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.AccountCircle,
                            title = "Hasta 5 miembros",
                            subtitle = "Comparte con tu familia"
                        )
                        BenefitItem(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Refresh,
                            title = "Sincronización en tiempo real",
                            subtitle = "Actualización instantánea"
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BenefitItem(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Lock,
                            title = "Almacenamiento seguro",
                            subtitle = "Tus datos protegidos"
                        )
                        BenefitItem(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Info,
                            title = "Almacenista IA",
                            subtitle = "Asistente inteligente 24/7"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Beta Info Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F2), RoundedCornerShape(16.dp))
                    .border(1.5.dp, Color(0xFFE89E5B).copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE89E5B).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFFE89E5B))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Acceso Beta Exclusivo",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Estás usando Domumtech durante nuestro período beta. Disfruta de todas las funcionalidades premium sin costo mientras ayudas a mejorar la aplicación.",
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun BenefitItem(modifier: Modifier = Modifier, icon: ImageVector, title: String, subtitle: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFDC7176).copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color(0xFFDC7176), modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A), textAlign = TextAlign.Center)
        )
        Text(
            text = subtitle,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 1250)
@Composable
fun PlanesMembresiaScreenPreview() {
    val navController = rememberNavController()
    PlanesMembresiaScreen(navController = navController)
}
