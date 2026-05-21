package com.escom.domumtech.ui.screens
import com.escom.domumtech.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.escom.domumtech.ui.theme.SetupEdgeToEdge
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListaComprasScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    var selectedCategory by remember { mutableStateOf("Todas") }
    SetupEdgeToEdge()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        // Header con Filtros
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.dynamicGradient(), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .statusBarsPadding()
                .padding(24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(//Tiene que ser boton
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver),
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(R.string.lista_compras_title),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.filtrar),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Categorías Chips
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.filtrar_categoria),
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White)
                            )
                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        val categories = listOf("Todas", "Bebidas", "Limpieza", "Condimentos", "Harinas", "Higiene", "Endulzantes")//Pendientes de agregar a las strings, hay que discutir si estas las decidimos nosotros o el usuario
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            categories.forEach { category ->
                                val isSelected = selectedCategory == category
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.2f))
                                        .clickable { selectedCategory = category }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = category,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (isSelected) Color(0xFF1A1A1A) else Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Sección "Se Terminó"
            ShoppingSection(
                title = stringResource(R.string.se_termino),
                count = "3",
                icon = Icons.Default.Info,
                iconColor = Color(0xFFE7000B),
                items = listOf(
                    ShoppingItemData("Azúcar", "Endulzantes", "0", Color(0xFFFB2C36)),
                    ShoppingItemData("Café Molido", "Bebidas", "0", Color(0xFFFB2C36)),
                    ShoppingItemData("Papel Higiénico", "Limpieza", "0", Color(0xFFFB2C36))
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Sección "Por Terminarse"
            ShoppingSection(
                title = stringResource(R.string.por_terminarse),
                count = "4",
                icon = Icons.Default.Warning,
                iconColor = Color(0xFFF54900),
                items = listOf(
                    ShoppingItemData("Sal", "Condimentos", "1", Color(0xFFFF6900)),
                    ShoppingItemData("Harina", "Harinas", "1", Color(0xFFFF6900))
                )
            )
        }
    }
}

data class ShoppingItemData(val name: String, val category: String, val stock: String, val borderColor: Color)

@Composable
fun ShoppingSection(title: String, count: String, icon: ImageVector, iconColor: Color, items: List<ShoppingItemData>) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(text = count, color = iconColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(14.dp))
                    .border(1.5.dp, item.borderColor.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.cardsColor())
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(text = item.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground))
                            Text(text = item.category, style = TextStyle(fontSize = 14.sp, color = Color.Gray))
                        }
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(item.borderColor.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = item.stock, color = item.borderColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        StoreButton(
                            modifier = Modifier.weight(1f),
                            label = "Amazon",
                            color = Color(0xFFFF9900)
                        )
                        StoreButton(
                            modifier = Modifier.weight(1f),
                            label = "Walmart",
                            color = Color(0xFF0071DC)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StoreButton(modifier: Modifier = Modifier, label: String, color: Color) {
    Button(
        onClick = { },
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Tu nuevo botón de Google integrado
    Button(
        onClick = { /* TODO: Agregar acción de inicio de sesión con Google */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(4.dp, RoundedCornerShape(14.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        )
    ) {
        Text(
            text = "Google",
            color = Color.Black // Cambiado a negro para que contraste con el fondo blanco
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true, widthDp = 393, heightDp = 1200)
@Composable
fun ListaComprasScreenPreview() {
    val navController = rememberNavController()
    ListaComprasScreen(navController = navController)
}
