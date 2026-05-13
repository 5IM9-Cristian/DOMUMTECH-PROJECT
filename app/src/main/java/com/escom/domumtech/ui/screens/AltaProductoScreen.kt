package com.escom.domumtech.ui.screens
import com.escom.domumtech.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.navigation.Screen
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.SetupEdgeToEdge
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.escaner
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun AltaProductoScreen(navController: NavController) {
    // SetupEdgeToEdge() // Deshabilitado para Preview

    val scrollState = rememberScrollState()
    
    // Estados para los campos
    var productName by remember { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(1) }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Button(
                    onClick = { 
                        // Aquí iría la lógica para guardar
                        navController.navigate(Screen.Dashboard.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(14.dp),
                    enabled = productName.isNotBlank() // Solo habilitar si hay nombre
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(
                            if (productName.isNotBlank()) MaterialTheme.colorScheme.dynamicGradient() else Brush.linearGradient(listOf(Color.LightGray, Color.LightGray))
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.agregar_inventario),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
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
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.dynamicGradient())
                    .statusBarsPadding()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 0.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                        text = stringResource(R.string.alta_producto_title),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                }
            }

            Column(modifier = Modifier.padding(24.dp)) {
                // Escáner OCR Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.cardsColor())
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = stringResource(R.string.escaner),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .background(MaterialTheme.colorScheme.escaner(), RoundedCornerShape(14.dp))
                                .border(1.5.dp, Color(0xFFE89E5B).copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                                .clickable { 
                                    // Simulación de OCR
                                    productName = "Leche Entera" //No tiene stringResource porque es provisional
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = stringResource(R.string.escaner_message),
                                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFFE89E5B))
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Detalles del Producto Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.cardsColor())
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = stringResource(R.string.detalle_producto_nombre),
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = productName,
                            onValueChange = { productName = it },
                            placeholder = { Text("Ej: Arroz Integral", color = MaterialTheme.colorScheme.placeholderColor()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
                            ),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = stringResource(R.string.detalle_producto_cantidad),
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                            ) {
                                Box(modifier = Modifier.width(16.dp).height(2.dp).background(Color.White))
                            }
                            Text(
                                text = quantity.toString(),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onBackground)
                            )
                            IconButton(
                                onClick = { quantity++ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color(0xFFE89E5B).copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                            ) {
                                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.more), tint = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AltaProductoPreview(){
    DomumtechTheme() {
        AltaProductoScreen(navController = rememberNavController())
    }
}
