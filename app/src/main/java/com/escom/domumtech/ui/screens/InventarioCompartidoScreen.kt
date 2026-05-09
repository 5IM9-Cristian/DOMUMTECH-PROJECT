package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InventarioCompartidoScreen() {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header con Miembros Activos
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .padding(24.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Inventario Compartido",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Miembros Activos Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Miembros Activos",
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ActiveMemberAvatar(emoji = "👩", name = "María", isActive = true)
                            ActiveMemberAvatar(emoji = "👨", name = "Juan", isActive = true)
                            ActiveMemberAvatar(emoji = "👧", name = "Ana", isActive = true)
                            ActiveMemberAvatar(emoji = "👦", name = "Pedro", isActive = true)
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.List, contentDescription = null, tint = Color(0xFF1A1A1A), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Productos Disponibles",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Productos
            val products = listOf(
                SharedProduct("Arroz Integral", "Granos", 2, "María"),
                SharedProduct("Aceite de Oliva", "Aceites", 1, "Juan"),
                SharedProduct("Frijoles Negros", "Granos", 3, "María"),
                SharedProduct("Pasta Italiana", "Pasta", 4, "Pedro"),
                SharedProduct("Tomate en Lata", "Enlatados", 5, "Ana"),
                SharedProduct("Leche Descremada", "Lácteos", 2, "María")
            )

            products.forEach { product ->
                SharedProductCard(product)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

data class SharedProduct(val name: String, val category: String, val stock: Int, val updatedBy: String)

@Composable
fun ActiveMemberAvatar(emoji: String, name: String, isActive: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(if (isActive) Color.White else Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, fontSize = 12.sp, color = Color.White)
        if (isActive) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFF05DF72), CircleShape)
            )
        }
    }
}

@Composable
fun SharedProductCard(product: SharedProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = product.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDC7176).copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = product.stock.toString(), color = Color(0xFFDC7176), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color(0xFFE89E5B).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = product.category, color = Color(0xFFE89E5B), fontSize = 12.sp)
                }
                Text(
                    text = "Actualizado por ${product.updatedBy}",
                    style = TextStyle(fontSize = 12.sp, color = Color.LightGray)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun InventarioCompartidoScreenPreview() {
    InventarioCompartidoScreen()
}
