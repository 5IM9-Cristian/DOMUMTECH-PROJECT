package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.escom.domumtech.R
import com.escom.domumtech.navigation.Screen
import com.escom.domumtech.ui.theme.dynamicGradient
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()

    // Estado para el diálogo de Cerrar Sesión
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }) {
                    Text("Cerrar Sesión", color = Color(0xFFDC7176))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar", color = Color.Gray)
                }
            },
            title = { Text("¿Cerrar Sesión?") },
            text = { Text("¿Estás seguro de que deseas salir de tu cuenta?") },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                drawerShape = RoundedCornerShape(0.dp),
                modifier = Modifier.width(320.dp)
            ) {
                DrawerContent(
                    gradient = mainGradient, 
                    navController = navController, 
                    drawerState = drawerState,
                    onLogoutClick = { showLogoutDialog = true }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
        ) {
            // Header con Gradiente y Stats
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        mainGradient,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .statusBarsPadding()
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Botón de Menú
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                .clickable { scope.launch { drawerState.open() } },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Hola, María",
                                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.White)
                            )
                            Text(
                                text = "¿Qué deseas hacer hoy?",
                                style = TextStyle(fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                    .clickable { navController.navigate(Screen.Ayuda.route) },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Info, contentDescription = "Ayuda", tint = Color.White)
                            }
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                    .clickable { navController.navigate(Screen.MiCuenta.route) },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Stats Cards
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        StatCard(modifier = Modifier.weight(1f), label = "Total productos", value = "47")
                        StatCard(modifier = Modifier.weight(1f), label = "Por comprar", value = "12")
                        StatCard(modifier = Modifier.weight(1f), label = "Miembros", value = "4")
                    }
                }
            }

            // Sección de Acceso Rápido
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                    Text(
                        text = "Acceso Rápido",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                ActionItem(
                    title = "Alta de Producto", 
                    subtitle = "Escanear o ingresar manualmente", 
                    icon = Icons.Default.AddCircle,
                    onClick = { navController.navigate(Screen.AltaProducto.route) }
                )
                ActionItem(
                    title = "Baja de Producto", 
                    subtitle = "Retirar productos del inventario", 
                    icon = Icons.Default.Delete,
                    onClick = { navController.navigate(Screen.BajaProducto.route) }
                )
                ActionItem(
                    title = "Inventario Compartido", 
                    subtitle = "Ver productos de la familia", 
                    icon = Icons.Default.Face,
                    onClick = { navController.navigate(Screen.InventarioCompartido.route) }
                )
                ActionItem(
                    title = "Lista de Compras", 
                    subtitle = "Productos por adquirir", 
                    icon = Icons.Default.ShoppingCart,
                    onClick = { navController.navigate(Screen.ListaCompras.route) }
                )
                ActionItem(
                    title = "Almacenista Virtual", 
                    subtitle = "Asistente inteligente", 
                    icon = Icons.Default.Info,
                    onClick = { navController.navigate(Screen.AlmacenistaChat.route) }
                )
            }
        }
    }
}

@Composable
fun DrawerContent(
    gradient: Brush, 
    navController: NavController, 
    drawerState: DrawerState,
    onLogoutClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    fun navigate(route: String) {
        scope.launch {
            drawerState.close()
            navController.navigate(route)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Menú",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    )
                    IconButton(onClick = { scope.launch { drawerState.close() } }) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(14.dp))
                        .clickable { navigate(Screen.MiCuenta.route) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👩", fontSize = 20.sp)
                    }
                    Column {
                        Text(
                            text = "María",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)
                        )
                        Text(
                            text = "Ver perfil",
                            style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f))
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 8.dp)
        ) {
            DrawerItem(icon = Icons.Default.Home, label = "Inicio", onClick = { navigate(Screen.Dashboard.route) })
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.AddCircle, label = "Alta de Producto", onClick = { navigate(Screen.AltaProducto.route) })
            DrawerItem(icon = Icons.Default.Delete, label = "Baja de Producto", onClick = { navigate(Screen.BajaProducto.route) })
            DrawerItem(icon = Icons.Default.Face, label = "Inventario Compartido", onClick = { navigate(Screen.InventarioCompartido.route) })
            DrawerItem(icon = Icons.Default.ShoppingCart, label = "Lista de Compras", onClick = { navigate(Screen.ListaCompras.route) })
            DrawerItem(icon = Icons.Default.Info, label = "Almacenista Virtual", onClick = { navigate(Screen.AlmacenistaChat.route) })
            
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.Person, label = "Mi Cuenta", onClick = { navigate(Screen.MiCuenta.route) })
            DrawerItem(icon = Icons.Default.Info, label = "Ayuda", onClick = { navigate(Screen.Ayuda.route) })
            
            Spacer(modifier = Modifier.weight(1f))
            
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.Settings, label = "Configuración", onClick = { navigate(Screen.Configuracion.route) })
            DrawerItem(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                label = "Cerrar Sesión",
                labelColor = Color(0xFFE7000B),
                iconTint = Color(0xFFE7000B),
                onClick = { 
                    scope.launch { drawerState.close() }
                    onLogoutClick() 
                }
            )
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    labelColor: Color = Color(0xFF1A1A1A),
    iconTint: Color = Color.Gray,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
        Text(
            text = label,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = labelColor)
        )
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, label: String, value: String) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = TextStyle(fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center)
        )
        Text(
            text = value,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal, color = Color.White, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun ActionItem(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(Color(0xFFFFF9F2), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = Color(0xFFF2A666), modifier = Modifier.size(24.dp))
            }
            Column {
                Text(text = title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A)))
                Text(text = subtitle, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFFE89E5B)))
            }
        }
    }
}
