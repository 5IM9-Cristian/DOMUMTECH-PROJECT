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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun AyudaScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val cardColor = MaterialTheme.colorScheme.cardsColor()
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
                Column {
                    Text(
                        text = "Centro de Ayuda",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                    Text(
                        text = "¿Cómo podemos ayudarte hoy?",
                        style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // FAQ Section
            Text(
                text = "Preguntas Frecuentes",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FaqItem("¿Cómo agrego un nuevo miembro?", "Puedes hacerlo desde la pantalla de Miembros en el menú de configuración.")
            FaqItem("¿El escáner OCR funciona con todos los productos?", "Funciona con la mayoría de las etiquetas claras, aunque estamos mejorando la IA cada día.")
            FaqItem("¿Mis datos están seguros?", "Sí, toda tu información está encriptada y solo es visible para ti y tus miembros invitados.")
            
            Spacer(modifier = Modifier.height(32.dp))

            // Contact Section
            Text(
                text = "Contáctanos",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onBackgroundColor),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ContactItem(Icons.Default.Email, "Soporte vía Email", "soporte@domumtech.com")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = placeholderColor.copy(alpha = 0.2f))
                    ContactItem(Icons.Default.Phone, "Llamada de Soporte", "+52 55 1234 5678")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun FaqItem(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded }
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = onBackgroundColor.copy(alpha = 0.5f)
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = answer,
                    style = TextStyle(fontSize = 14.sp, color = placeholderColor, lineHeight = 20.sp)
                )
            }
        }
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, value: String) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(40.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor)
            Text(text = value, fontSize = 14.sp, color = placeholderColor)
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun AyudaScreenPreview() {
    DomumtechTheme {
        AyudaScreen(navController = rememberNavController())
    }
}
