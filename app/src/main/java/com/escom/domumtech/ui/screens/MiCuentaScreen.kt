package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun MiCuentaScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }) {
                    Text(stringResource(R.string.cerrar_sesion), color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text(stringResource(R.string.volver), color = placeholderColor)
                }
            },
            title = { Text(stringResource(R.string.cerrar_sesion) + "?") },
            text = { Text("¿Estás seguro de que deseas salir de tu cuenta?") },
            containerColor = cardColor,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .statusBarsPadding()
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
                            contentDescription = stringResource(R.string.volver),
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.drawer_menu_my_account),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }

                // Profile Card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👩", fontSize = 24.sp)
                    }
                    Column {
                        Text(
                            text = "María González",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
                        )
                        Text(
                            text = "maria@email.com",
                            style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            SectionHeader(title = "Información personal")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoItem(label = "Nombre completo", value = "María González", icon = Icons.Default.Person)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = placeholderColor.copy(alpha = 0.2f))
                    InfoItem(label = "Correo electrónico", value = "maria@email.com", icon = Icons.Default.Email)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = placeholderColor.copy(alpha = 0.2f))
                    InfoItem(label = "+52 55 1234 5678", value = "Teléfono", icon = Icons.Default.Phone)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Miembros del grupo",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
                )
                Button(
                    onClick = { navController.navigate(Screen.MiembrosPlan.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text("Gestionar miembros", fontSize = 12.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    MemberItem(name = "Juan Pérez", email = "juan@email.com", role = "Admin", initial = "J")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = placeholderColor.copy(alpha = 0.2f))
                    MemberItem(name = "Ana García", email = "ana@email.com", role = "Miembro", initial = "A")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = placeholderColor.copy(alpha = 0.2f))
                    MemberItem(name = "Pedro López", email = "pedro@email.com", role = "Miembro", initial = "P")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            FooterOption(icon = Icons.Default.Star, label = "Planes y membresía", onClick = { navController.navigate(Screen.PlanesMembresia.route) })
            FooterOption(icon = Icons.Default.Settings, label = "Configuración", onClick = { navController.navigate(Screen.Configuracion.route) })
            FooterOption(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                label = stringResource(R.string.cerrar_sesion),
                color = MaterialTheme.colorScheme.secondary,
                onClick = { showLogoutDialog = true }
            )
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(bottom = 16.dp),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
fun InfoItem(label: String, value: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.placeholderColor())
            Text(text = value, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun MemberItem(name: String, email: String, role: String, initial: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(text = initial, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = name, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
                Text(text = email, fontSize = 12.sp, color = MaterialTheme.colorScheme.placeholderColor())
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = role, fontSize = 12.sp, color = MaterialTheme.colorScheme.placeholderColor())
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFF05DF72), CircleShape)
            )
        }
    }
}

@Composable
fun FooterOption(icon: ImageVector, label: String, color: Color = MaterialTheme.colorScheme.onBackground, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun MiCuentaScreenPreview() {
    DomumtechTheme {
        MiCuentaScreen(navController = rememberNavController())
    }
}
