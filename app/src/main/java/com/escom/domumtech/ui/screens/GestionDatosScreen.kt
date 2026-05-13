package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun GestionDatosScreen(navController: NavController) {
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
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient)
                .padding(24.dp)
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Gestión de Datos",
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                        )
                        Text(
                            text = "Controla tu información personal",
                            style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Tus Derechos Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F2), RoundedCornerShape(14.dp))
                    .border(1.5.dp, Color(0xFFCDD7D8).copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFFDC7176), modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Tus Derechos sobre tus Datos",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Tienes control total sobre tu información. Puedes exportar todos tus datos en cualquier momento o eliminar tu cuenta permanentemente.",
                        style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xCC1A1A1A))
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sección Exportar Datos
            Text(
                text = "Exportar Datos",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFFE89E5B).copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color(0xFFE89E5B))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Descargar mi Información",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Recibirás un archivo con todos tus datos en formato JSON, incluyendo:",
                        style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xB21A1A1A))
                    )
                    
                    val items = listOf(
                        "Información de perfil",
                        "Inventario completo de productos",
                        "Historial de altas y bajas",
                        "Listas de compras guardadas",
                        "Conversaciones con el Almacenista",
                        "Configuraciones y preferencias"
                    )
                    
                    Column(modifier = Modifier.padding(start = 8.dp, top = 12.dp)) {
                        items.forEach { item ->
                            Text(
                                text = "• $item",
                                style = TextStyle(fontSize = 14.sp, lineHeight = 24.sp, color = Color(0xB21A1A1A))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { },
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
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Exportar Todos mis Datos", color = Color.White, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "El proceso puede tardar unos minutos. Te enviaremos un correo cuando esté listo.",
                        style = TextStyle(fontSize = 12.sp, color = Color.LightGray, textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Sección Eliminar Cuenta
            Text(
                text = "Eliminar Cuenta",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFEF2F2), RoundedCornerShape(14.dp))
                    .border(1.5.dp, Color(0xFFFFC9C9), RoundedCornerShape(14.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFFFB2C36), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Zona de Peligro",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC10007))
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "La eliminación de tu cuenta es permanente e irreversible. Esta acción:",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xCCE7000B))
                    )
                    
                    val warningItems = listOf(
                        "Eliminará todo tu inventario de productos",
                        "Removerá tu acceso a grupos compartidos",
                        "Borrará tu historial completo",
                        "Cancelará todas las suscripciones activas",
                        "No podrá deshacerse después de 30 días"
                    )
                    
                    Column(modifier = Modifier.padding(start = 8.dp, top = 12.dp)) {
                        warningItems.forEach { item ->
                            Text(
                                text = "• $item",
                                style = TextStyle(fontSize = 14.sp, lineHeight = 24.sp, color = Color(0xCCE7000B))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = borderStroke(1.5.dp, Color(0xFFFB2C36)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE7000B))
                    ) {
                        Text("Quiero Eliminar mi Cuenta", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sección Ayuda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCDD7D8).copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "¿Necesitas Ayuda?",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A1A1A))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Si tienes dudas sobre la gestión de tus datos o necesitas asistencia, nuestro equipo de soporte está disponible para ayudarte.",
                        style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xB21A1A1A))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Email: soporte@domumtech.com", fontSize = 14.sp, color = Color(0xFF1A1A1A))
                    Text(text = "Teléfono: +52 55 1234 5678", fontSize = 14.sp, color = Color(0xFF1A1A1A))
                    Text(text = "Horario: Lun-Vie 9:00 - 18:00", fontSize = 14.sp, color = Color(0xFF1A1A1A))
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun borderStroke(width: androidx.compose.ui.unit.Dp, color: Color) = 
    androidx.compose.foundation.BorderStroke(width, color)

@Preview(showBackground = true, widthDp = 393, heightDp = 1800)
@Composable
fun GestionDatosScreenPreview() {
    val navController = rememberNavController()
    GestionDatosScreen(navController = navController)
}
