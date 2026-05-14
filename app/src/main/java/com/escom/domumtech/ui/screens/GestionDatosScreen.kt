package com.escom.domumtech.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.R
import com.escom.domumtech.navigation.Screen
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor
import kotlinx.coroutines.launch

@Composable
fun GestionDatosScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()
    val secondaryColor = MaterialTheme.colorScheme.secondary

    // Estado para el diálogo de Eliminar Cuenta
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(Screen.Dashboard.route) { inclusive = true }
                        }
                    }) {
                        Text("Eliminar para siempre", color = MaterialTheme.colorScheme.primary)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text(stringResource(R.string.volver), color = placeholderColor)
                    }
                },
                title = { Text("¿Eliminar tu cuenta?") },
                text = { Text("Esta acción es irreversible y borrará todo tu inventario y datos familiares. ¿Estás absolutamente seguro?") },
                containerColor = cardColor,
                shape = RoundedCornerShape(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mainGradient)
                    .statusBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
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
                                contentDescription = stringResource(R.string.volver),
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Gestión de datos",
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
                        .background(cardColor, RoundedCornerShape(14.dp))
                        .border(1.5.dp, secondaryColor.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Tus derechos sobre tus datos",
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Tienes control total sobre tu información. Puedes exportar todos tus datos en cualquier momento o eliminar tu cuenta permanentemente.",
                            style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = onBackgroundColor.copy(alpha = 0.7f))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección Exportar Datos
                Text(
                    text = "Exportar datos",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(secondaryColor.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = secondaryColor)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Descargar mi información",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Recibirás un archivo con todos tus datos en formato JSON, incluyendo:",
                            style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = placeholderColor)
                        )
                        
                        val items = listOf(
                            "Información de perfil",
                            "Inventario completo de productos",
                            "Historial de altas y bajas",
                            "Listas de compras guardadas",
                            "Conversaciones con el almacenista",
                            "Configuraciones y preferencias"
                        )
                        
                        Column(modifier = Modifier.padding(start = 8.dp, top = 12.dp)) {
                            items.forEach { item ->
                                Text(
                                    text = "• $item",
                                    style = TextStyle(fontSize = 14.sp, lineHeight = 24.sp, color = placeholderColor)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { 
                                scope.launch {
                                    snackbarHostState.showSnackbar("Exportación iniciada. Revisa tu correo pronto.")
                                }
                            },
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
                                    Text("Exportar todos mis datos", color = Color.White, fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "El proceso puede tardar unos minutos. Te enviaremos un correo cuando esté listo.",
                            style = TextStyle(fontSize = 12.sp, color = placeholderColor, textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Sección Eliminar Cuenta
                Text(
                    text = "Eliminar cuenta",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(cardColor, RoundedCornerShape(14.dp))
                        .border(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Zona de peligro",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "La eliminación de tu cuenta es permanente e irreversible. Esta acción:",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor.copy(alpha = 0.8f))
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
                                    style = TextStyle(fontSize = 14.sp, lineHeight = 24.sp, color = placeholderColor)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedButton(
                            onClick = { showDeleteDialog = true },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(14.dp),
                            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Quiero eliminar mi cuenta", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección Ayuda
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(cardColor, RoundedCornerShape(14.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "¿Necesitas ayuda?",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Si tienes dudas sobre la gestión de tus datos o necesitas asistencia, nuestro equipo de soporte está disponible para ayudarte.",
                            style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = placeholderColor)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Email: soporte@domumtech.com", fontSize = 14.sp, color = onBackgroundColor)
                        Text(text = "Teléfono: +52 55 1234 5678", fontSize = 14.sp, color = onBackgroundColor)
                        Text(text = "Horario: Lun-Vie 9:00 - 18:00", fontSize = 14.sp, color = onBackgroundColor)
                    }
                }
                
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GestionDatosScreenPreview() {
    DomumtechTheme {
        GestionDatosScreen(navController = rememberNavController())
    }
}
