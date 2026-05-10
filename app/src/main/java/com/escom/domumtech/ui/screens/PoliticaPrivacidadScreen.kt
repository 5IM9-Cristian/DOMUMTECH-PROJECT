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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PoliticaPrivacidadScreen() {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
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
                            text = "Política de Privacidad",
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                        )
                        Text(
                            text = "Protegemos tu información",
                            style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp)
        ) {
            // Intro Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F2), RoundedCornerShape(14.dp))
                    .border(1.5.dp, Color(0xFFCDD7D8).copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "En Domumtech, nos comprometemos a proteger tu privacidad y tus datos personales. Esta política describe cómo recopilamos, usamos y protegemos tu información.",
                    style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, color = Color(0xFF1A1A1A))
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            PrivacySection(
                title = "1. Información que Recopilamos",
                content = "Recopilamos los siguientes tipos de información:",
                subSections = listOf(
                    "Información de Cuenta" to listOf(
                        "Nombre y correo electrónico",
                        "Información de autenticación de Google",
                        "Fotografía de perfil (opcional)"
                    ),
                    "Datos de Inventario" to listOf(
                        "Productos registrados en tu despensa",
                        "Cantidades y categorías",
                        "Historial de altas y bajas",
                        "Listas de compras generadas"
                    ),
                    "Información de Uso" to listOf(
                        "Interacciones con el Almacenista Virtual",
                        "Frecuencia de uso de la aplicación",
                        "Preferencias de configuración"
                    )
                )
            )

            PrivacySection(
                title = "2. Cómo Usamos tu Información",
                content = "Utilizamos tu información para:",
                items = listOf(
                    "Proporcionar y mejorar nuestros servicios",
                    "Sincronizar tu inventario entre dispositivos",
                    "Permitir el inventario compartido con miembros de tu familia",
                    "Generar recomendaciones personalizadas",
                    "Enviar notificaciones sobre productos",
                    "Analizar el uso para mejorar la experiencia"
                )
            )

            PrivacySection(
                title = "3. Compartir Información",
                content = "No vendemos ni compartimos tu información personal con terceros, excepto en los siguientes casos:",
                items = listOf(
                    "Con miembros de tu grupo familiar (inventario compartido)",
                    "Con proveedores de servicios que nos ayudan a operar la aplicación",
                    "Cuando sea requerido por ley o para proteger nuestros derechos",
                    "En caso de fusión o adquisición (te notificaremos previamente)"
                )
            )

            // Security Grid Section
            Text(
                text = "4. Seguridad de Datos",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Implementamos medidas de seguridad técnicas y organizativas para proteger tu información:",
                style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, color = Color(0xCC1A1A1A))
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SecurityChip(text = "🔒 Cifrado SSL/TLS", modifier = Modifier.weight(1f))
                SecurityChip(text = "🔐 Autenticación segura", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SecurityChip(text = "💾 Backups regulares", modifier = Modifier.weight(1f))
                SecurityChip(text = "👥 Acceso limitado", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(color = Color(0xFFCDD7D8).copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(32.dp))

            PrivacySection(
                title = "5. Tus Derechos",
                content = "Tienes derecho a:",
                items = listOf(
                    "Acceder a tu información personal",
                    "Corregir datos inexactos",
                    "Solicitar la eliminación de tu cuenta y datos",
                    "Exportar tu información en formato portátil",
                    "Optar por no recibir comunicaciones de marketing",
                    "Retirar tu consentimiento en cualquier momento"
                ),
                footer = "Para ejercer estos derechos, visita la sección de Gestión de Datos en Configuración."
            )

            PrivacySection(
                title = "6. Cookies y Tecnologías Similares",
                content = "Utilizamos cookies y tecnologías similares para mejorar tu experiencia, recordar tus preferencias y analizar el uso de la aplicación. Puedes gestionar las cookies desde la configuración de tu navegador."
            )

            PrivacySection(
                title = "7. Actualizaciones de la Política",
                content = "Podemos actualizar esta política ocasionalmente. Te notificaremos sobre cambios importantes por correo electrónico o mediante un aviso en la aplicación."
            )

            Text(
                text = "8. Contacto",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCDD7D8).copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(text = "Para cualquier consulta sobre privacidad:", style = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Email: privacidad@domumtech.com", style = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A)))
                    Text(text = "Teléfono: +52 55 1234 5678", style = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A)))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Última actualización: 1 de Mayo de 2026",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 14.sp, color = Color.LightGray, textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PrivacySection(
    title: String,
    content: String,
    items: List<String> = emptyList(),
    subSections: List<Pair<String, List<String>>> = emptyList(),
    footer: String? = null
) {
    Column(modifier = Modifier.padding(bottom = 32.dp)) {
        Text(text = title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A)))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = content, style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, color = Color(0xCC1A1A1A)))
        
        if (items.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
                BulletItem(item)
            }
        }

        if (subSections.isNotEmpty()) {
            subSections.forEach { (subTitle, subItems) ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = subTitle, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A)))
                subItems.forEach { item ->
                    BulletItem(item)
                }
            }
        }

        if (footer != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = footer, style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, color = Color(0xCC1A1A1A)))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.2f))
    }
}

@Composable
fun BulletItem(text: String) {
    Row(modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp), verticalAlignment = Alignment.Top) {
        Text(text = "•", modifier = Modifier.padding(end = 12.dp), style = TextStyle(fontSize = 16.sp, color = Color(0xCC1A1A1A)))
        Text(text = text, style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xCC1A1A1A)))
    }
}

@Composable
fun SecurityChip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0x33CDD7D8), RoundedCornerShape(14.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = TextStyle(fontSize = 14.sp, color = Color(0xFF1A1A1A)), textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 1500)
@Composable
fun PoliticaPrivacidadScreenPreview() {
    PoliticaPrivacidadScreen()
}
