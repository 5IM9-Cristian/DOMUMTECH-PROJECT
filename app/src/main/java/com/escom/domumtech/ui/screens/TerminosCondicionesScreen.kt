package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TerminosCondicionesScreen(navController: NavController) {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Términos y Condiciones",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
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
            LegalSection(
                title = "1. Aceptación de los Términos",
                content = "Al acceder y utilizar Domumtech (\"la Aplicación\"), usted acepta estar sujeto a estos Términos y Condiciones de Uso. Si no está de acuerdo con alguna parte de estos términos, no debe utilizar nuestra aplicación."
            )

            LegalSection(
                title = "2. Descripción del Servicio",
                content = "Domumtech es una aplicación de gestión de inventario doméstico que permite a los usuarios:",
                items = listOf(
                    "Registrar y dar de baja productos de su despensa",
                    "Compartir inventario con miembros de su familia",
                    "Generar listas de compras inteligentes",
                    "Utilizar un asistente virtual (Almacenista) para gestión de productos"
                )
            )

            LegalSection(
                title = "3. Registro y Seguridad de Cuenta",
                content = "Para utilizar ciertas funciones de la Aplicación, debe crear una cuenta. Usted es responsable de:",
                items = listOf(
                    "Mantener la confidencialidad de su contraseña",
                    "Todas las actividades que ocurran bajo su cuenta",
                    "Notificarnos inmediatamente sobre cualquier uso no autorizado",
                    "Proporcionar información precisa y actualizada"
                )
            )

            LegalSection(
                title = "4. Uso Aceptable",
                content = "Usted se compromete a no utilizar la Aplicación para:",
                items = listOf(
                    "Violar leyes locales, estatales, nacionales o internacionales",
                    "Transmitir contenido ofensivo, difamatorio o inapropiado",
                    "Intentar acceder a áreas no autorizadas del sistema",
                    "Interferir con el funcionamiento de la Aplicación",
                    "Utilizar la Aplicación con fines comerciales sin autorización"
                )
            )

            LegalSection(
                title = "5. Propiedad Intelectual",
                content = "Todo el contenido de la Aplicación, incluyendo pero no limitado a texto, gráficos, logotipos, iconos, imágenes y software, es propiedad de Domumtech o sus licenciantes y está protegido por las leyes de propiedad intelectual."
            )

            LegalSection(
                title = "6. Limitación de Responsabilidad",
                content = "Domumtech se proporciona \"tal cual\" sin garantías de ningún tipo. No nos hacemos responsables por:",
                items = listOf(
                    "Pérdida de datos o información",
                    "Interrupciones del servicio",
                    "Errores o inexactitudes en el contenido",
                    "Daños directos, indirectos o consecuentes"
                )
            )

            LegalSection(
                title = "7. Modificaciones",
                content = "Nos reservamos el derecho de modificar estos términos en cualquier momento. Las modificaciones entrarán en vigor al momento de su publicación en la Aplicación. Su uso continuado constituye aceptación de los términos modificados."
            )

            LegalSection(
                title = "8. Contacto",
                content = "Para cualquier pregunta sobre estos Términos y Condiciones, puede contactarnos en:"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Contact Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCDD7D8).copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Email: legal@domumtech.com",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Teléfono: +52 55 1234 5678",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A))
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Última actualización: 1 de Mayo de 2026",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LegalSection(title: String, content: String, items: List<String> = emptyList()) {
    Column(modifier = Modifier.padding(bottom = 32.dp)) {
        Text(
            text = title,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = content,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 26.sp,
                color = Color(0xCC1A1A1A)
            )
        )
        if (items.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
                Row(
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        modifier = Modifier.padding(end = 12.dp),
                        style = TextStyle(fontSize = 16.sp, color = Color(0xCC1A1A1A))
                    )
                    Text(
                        text = item,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = Color(0xCC1A1A1A)
                        )
                    )
                }
            }
        }
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp), color = Color.LightGray.copy(alpha = 0.2f))
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 1800)
@Composable
fun TerminosCondicionesScreenPreview() {
    val navController = rememberNavController()
    TerminosCondicionesScreen(navController = navController)
}
