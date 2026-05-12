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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.ui.theme.dynamicGradient
import androidx.compose.ui.res.stringResource
import com.escom.domumtech.ui.theme.cardsColor

@Composable
fun BajaProductoScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    
    val products = listOf(
        "Arroz Integral" to 2,
        "Aceite de Oliva" to 1,
        "Frijoles Negros" to 3,
        "Pasta Italiana" to 4
    )
    
    var selectedProduct by remember { mutableStateOf("Arroz Integral") }
    var quantityToRemove by remember { mutableIntStateOf(1) }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.dynamicGradient()),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.dar_baja),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                //.statusBarsPadding()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Header con Gradiente
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.dynamicGradient())
                    .padding(24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volver),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.dar_baja_producto),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }
            }

            Column(modifier = Modifier.padding(24.dp)) {
                // Seleccionar Producto Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.cardsColor())
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = stringResource(R.string.seleccionar_producto),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        products.forEach { (name, stock) ->
                            val isSelected = selectedProduct == name
                            val itemModifier = if (isSelected) {
                                Modifier.background(MaterialTheme.colorScheme.dynamicGradient())
                            } else {
                                Modifier.background(Color.Transparent)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .then(itemModifier)
                                    .clickable { selectedProduct = name }
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = name,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
                                    )
                                )
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Transparent),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stock.toString(),
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Cantidad a dar de baja Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.cardsColor())
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = stringResource(R.string.cantidad_baja),
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            IconButton(
                                onClick = { if (quantityToRemove > 1) quantityToRemove-- },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                            ) {
                                Box(modifier = Modifier.width(16.dp).height(2.dp).background(Color.White))
                            }
                            Text(
                                text = quantityToRemove.toString(),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                            )
                            IconButton(
                                onClick = { quantityToRemove++ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                            ) {
                                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.more), tint = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Info Box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFFFF9F2), RoundedCornerShape(14.dp))
                                .border(0.9.dp, Color(0x4DCDD7D8), RoundedCornerShape(14.dp))
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    text = "${stringResource(R.string.producto_seleccionado)} $selectedProduct",
                                    style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
                                )
                                val currentStock = products.find { it.first == selectedProduct }?.second ?: 0
                                Text(
                                    text = "${stringResource(R.string.disponible)} $currentStock",
                                    style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun BajaProductoScreenPreview() {
    val navController = rememberNavController()
    BajaProductoScreen(navController = navController)
}
