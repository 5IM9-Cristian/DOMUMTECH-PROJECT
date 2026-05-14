package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun TerminosCondicionesScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

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
                Text(
                    text = "Términos y Condiciones",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                )
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Última actualización: Diciembre 2026",
                style = TextStyle(fontSize = 14.sp, color = placeholderColor)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            LegalSection("1. Aceptación de los Términos", "Al utilizar Domumtech, aceptas cumplir con estos términos. Si no estás de acuerdo, por favor no utilices la aplicación.")
            LegalSection("2. Uso del Servicio", "Domumtech es una herramienta de gestión de inventario familiar. El usuario es responsable de la veracidad de los datos ingresados.")
            LegalSection("3. Propiedad Intelectual", "Todo el contenido y software relacionado con Domumtech es propiedad de ESCOM y sus desarrolladores.")
            LegalSection("4. Limitación de Responsabilidad", "No nos hacemos responsables por pérdidas de datos debidas a mal uso de la aplicación o fallas en el dispositivo del usuario.")
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LegalSection(title: String, content: String) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = TextStyle(fontSize = 15.sp, color = placeholderColor, lineHeight = 22.sp)
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun TerminosCondicionesScreenPreview() {
    DomumtechTheme {
        TerminosCondicionesScreen(navController = rememberNavController())
    }
}
