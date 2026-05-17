package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.R
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.dynamicGradient

@Composable
fun NotaComprasScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background

    // Lista de productos agotados o bajos (simulados)
    var items by remember { 
        mutableStateOf(listOf(
            "Azúcar" to false, 
            "Café molido" to false, 
            "Papel higiénico" to false,
            "Sal" to false,
            "Harina" to false
        )) 
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .statusBarsPadding()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
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
                    text = "Nota de compras",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(24.dp)
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            // EFECTO DE HOJA DE PAPEL (SPRITE)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 12.dp, shape = RoundedCornerShape(2.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4)), // Amarillo Post-it
                shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomStart = 24.dp, bottomEnd = 4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lista para el súper",
                            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                        )
                        IconButton(onClick = { /* Compartir PDF */ }) {
                            Icon(Icons.Default.Share, contentDescription = "Exportar", tint = Color(0xFF5D4037))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    items.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { 
                                    val newList = items.toMutableList()
                                    newList[index] = item.first to !item.second
                                    items = newList
                                }
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (item.second) Icons.Default.CheckCircle else Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = if (item.second) Color(0xFF4CAF50) else Color(0xFF5D4037).copy(alpha = 0.3f),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = item.first,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = if (item.second) Color.Gray else Color(0xFF5D4037),
                                    textDecoration = if (item.second) TextDecoration.LineThrough else TextDecoration.None
                                )
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5D4037).copy(alpha = 0.1f))
                    }
                    
                    Spacer(modifier = Modifier.height(40.dp))
                    
                    Text(
                        text = "Generado por Domumtech",
                        style = TextStyle(fontSize = 12.sp, color = Color(0xFF5D4037).copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotaComprasScreenPreview() {
    DomumtechTheme {
        NotaComprasScreen(navController = rememberNavController())
    }
}
