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
import com.escom.domumtech.navigation.Screen
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun ConfiguracionScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

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
                .background(mainGradient)
                .statusBarsPadding()
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
                        text = "Configuración",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                    Text(
                        text = "Personaliza tu experiencia",
                        style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            ConfigSection(title = "General") {
                ConfigSwitchItem(
                    icon = Icons.Default.Notifications,
                    title = "Notificaciones",
                    subtitle = "Alertas de productos agotados",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = placeholderColor.copy(alpha = 0.2f))
                ConfigSwitchItem(
                    icon = Icons.Default.Settings,
                    title = "Modo Oscuro",
                    subtitle = "Cambiar apariencia visual",
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ConfigSection(title = "Cuenta y Seguridad") {
                ConfigNavigationItem(
                    icon = Icons.Default.Lock,
                    title = "Cambiar Contraseña",
                    onClick = { navController.navigate(Screen.CambioPassword.route) }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = placeholderColor.copy(alpha = 0.2f))
                ConfigNavigationItem(
                    icon = Icons.Default.Person,
                    title = "Gestión de Datos",
                    onClick = { navController.navigate(Screen.GestionDatos.route) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ConfigSection(title = "Información") {
                ConfigNavigationItem(
                    icon = Icons.Default.Info,
                    title = "Términos y Condiciones",
                    onClick = { navController.navigate(Screen.TerminosCondiciones.route) }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = placeholderColor.copy(alpha = 0.2f))
                ConfigNavigationItem(
                    icon = Icons.Default.Info,
                    title = "Política de Privacidad",
                    onClick = { navController.navigate(Screen.PoliticaPrivacidad.route) }
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ConfigSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground

    Column {
        Text(
            text = title,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor),
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun ConfigSwitchItem(icon: ImageVector, title: String, subtitle: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor)
                Text(text = subtitle, fontSize = 12.sp, color = placeholderColor)
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun ConfigNavigationItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor)
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = onBackgroundColor.copy(alpha = 0.3f))
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun ConfiguracionScreenPreview() {
    DomumtechTheme {
        ConfiguracionScreen(navController = rememberNavController())
    }
}
