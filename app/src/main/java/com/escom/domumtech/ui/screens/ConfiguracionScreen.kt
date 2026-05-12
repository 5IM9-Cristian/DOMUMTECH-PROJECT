package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConfiguracionScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )
    
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

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
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Configuración",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                )
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Sección Preferencias
            SectionHeader(title = "Preferencias")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SettingsSwitchRow(
                        icon = Icons.Default.Notifications,
                        title = "Notificaciones",
                        subtitle = "Alertas de productos",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                    SettingsSwitchRow(
                        icon = Icons.Default.Build,
                        title = "Modo Oscuro",
                        subtitle = "Apariencia de la app",
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it }
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                    SettingsClickableRow(
                        icon = Icons.Default.Settings,
                        title = "Idioma",
                        subtitle = "Español"
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sección Privacidad y Seguridad
            SectionHeader(title = "Privacidad y Seguridad")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SettingsClickableRow(
                        icon = Icons.Default.Lock,
                        title = "Cambiar Contraseña"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                    SettingsClickableRow(
                        icon = Icons.Default.Info,
                        title = "Gestión de Datos"
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sección Acerca de
            SectionHeader(title = "Acerca de")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SettingsInfoRow(
                        icon = Icons.Default.Info,
                        title = "Versión",
                        value = "1.0.0 Beta"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                    SettingsClickableRow(
                        title = "Términos y Condiciones"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.2f))
                    SettingsClickableRow(
                        title = "Política de Privacidad"
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Powered by Unfly",
                    style = TextStyle(fontSize = 14.sp, color = Color.LightGray)
                )
                Text(
                    text = "© 2026 Domumtech",
                    style = TextStyle(fontSize = 12.sp, color = Color.LightGray)
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SettingsSwitchRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color(0xFF1A1A1A))
                Text(text = subtitle, fontSize = 14.sp, color = Color(0xFFE89E5B).copy(alpha = 0.8f))
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFFDC7176),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SettingsClickableRow(
    icon: ImageVector? = null,
    title: String,
    subtitle: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                if (subtitle != null) {
                    Text(text = subtitle, fontSize = 14.sp, color = Color(0xFFE89E5B).copy(alpha = 0.8f))
                }
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.LightGray.copy(alpha = 0.5f),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun SettingsInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp, color = Color(0xFF1A1A1A))
        }
        Text(text = value, fontSize = 14.sp, color = Color(0xFFE89E5B).copy(alpha = 0.8f))
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun ConfiguracionScreenPreview() {
    val navController = rememberNavController()
    ConfiguracionScreen(navController = navController)
}
