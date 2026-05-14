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
fun PoliticaPrivacidadScreen(navController: NavController) {
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
                    text = "Política de Privacidad",
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

            LegalSection("1. Recopilación de Datos", "Recopilamos información básica como tu nombre y correo para la gestión de la cuenta, además de los datos de inventario que ingreses.")
            LegalSection("2. Uso de la Información", "Tus datos se utilizan exclusivamente para las funciones de la aplicación y la sincronización familiar. No vendemos tus datos a terceros.")
            LegalSection("3. Escaneo OCR", "Las imágenes procesadas por el escáner se utilizan solo para extraer el texto del producto y no se almacenan permanentemente en nuestros servidores.")
            LegalSection("4. Tus Derechos", "Puedes solicitar la exportación o eliminación de todos tus datos en cualquier momento desde la sección de Gestión de Datos.")
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun PoliticaPrivacidadScreenPreview() {
    DomumtechTheme {
        PoliticaPrivacidadScreen(navController = rememberNavController())
    }
}
