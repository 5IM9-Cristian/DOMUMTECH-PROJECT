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
import com.escom.domumtech.ui.components.shimmerEffect
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val cardColor = MaterialTheme.colorScheme.cardsColor()

    // Estados para simular carga
    var isLoading by remember { mutableStateOf(true) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600) // Carga rápida de 0.6 segundos
        isLoading = false
    }

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
                    Text(stringResource(R.string.cerrar_sesion), color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text(stringResource(R.string.volver), color = MaterialTheme.colorScheme.placeholderColor())
                }
            },
            title = { Text(stringResource(R.string.cerrar_sesion) + "?") },
            text = { Text("¿Estás seguro de que deseas salir de tu cuenta?") },
            containerColor = cardColor,
            shape = RoundedCornerShape(16.dp)
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = cardColor,
                drawerShape = RoundedCornerShape(0.dp),
                modifier = Modifier.width(320.dp),
                windowInsets = WindowInsets(0, 0, 0, 0)
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
                .background(backgroundColor)
                .verticalScroll(scrollState)
        ) {
            // Header
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
                                text = stringResource(R.string.hello),
                                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.White)
                            )
                            Text(
                                text = stringResource(R.string.question),
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
                        StatCard(modifier = Modifier.weight(1f), label = stringResource(R.string.total_productos), value = "47", isLoading = isLoading)
                        StatCard(modifier = Modifier.weight(1f), label = stringResource(R.string.por_comprar), value = "12", isLoading = isLoading)
                        StatCard(modifier = Modifier.weight(1f), label = stringResource(R.string.miembros), value = "4", isLoading = isLoading)
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
                        text = stringResource(R.string.quick_access),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.5f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    repeat(5) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(vertical = 8.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .shimmerEffect()
                        )
                    }
                } else {
                    ActionItem(
                        title = stringResource(R.string.alta_producto_title), 
                        subtitle = stringResource(R.string.alta_producto_subtitle), 
                        icon = Icons.Default.AddCircle,
                        onClick = { navController.navigate(Screen.AltaProducto.route) }
                    )
                    ActionItem(
                        title = stringResource(R.string.baja_producto_title), 
                        subtitle = stringResource(R.string.baja_producto_subtitle), 
                        icon = Icons.Default.Delete,
                        onClick = { navController.navigate(Screen.BajaProducto.route) }
                    )
                    ActionItem(
                        title = stringResource(R.string.inventario_compartido_title), 
                        subtitle = stringResource(R.string.inventario_compartido_subtitle), 
                        icon = Icons.Default.Face,
                        onClick = { navController.navigate(Screen.InventarioCompartido.route) }
                    )
                    ActionItem(
                        title = stringResource(R.string.lista_compras_title), 
                        subtitle = stringResource(R.string.lista_compras_subtitle), 
                        icon = Icons.Default.ShoppingCart,
                        onClick = { navController.navigate(Screen.ListaCompras.route) }
                    )
                    ActionItem(
                        title = stringResource(R.string.almacenista_virtual_title), 
                        subtitle = stringResource(R.string.almacenista_virtual_subtitle), 
                        icon = Icons.Default.Info,
                        onClick = { navController.navigate(Screen.AlmacenistaChat.route) }
                    )
                }
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

    val cardColor = MaterialTheme.colorScheme.cardsColor()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .statusBarsPadding()
                .padding(24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.drawer_menu),
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
                            text = stringResource(R.string.drawer_menu_profile),
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
            DrawerItem(icon = Icons.Default.Home, label = stringResource(R.string.drawer_menu_start), onClick = { navigate(Screen.Dashboard.route) })
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.AddCircle, label = stringResource(R.string.alta_producto_title), onClick = { navigate(Screen.AltaProducto.route) })
            DrawerItem(icon = Icons.Default.Delete, label = stringResource(R.string.baja_producto_title), onClick = { navigate(Screen.BajaProducto.route) })
            DrawerItem(icon = Icons.Default.Face, label = stringResource(R.string.inventario_compartido_title), onClick = { navigate(Screen.InventarioCompartido.route) })
            DrawerItem(icon = Icons.Default.ShoppingCart, label = stringResource(R.string.lista_compras_title), onClick = { navigate(Screen.ListaCompras.route) })
            DrawerItem(icon = Icons.Default.Info, label = stringResource(R.string.almacenista_virtual_title), onClick = { navigate(Screen.AlmacenistaChat.route) })
            
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.Person, label = stringResource(R.string.drawer_menu_my_account), onClick = { navigate(Screen.MiCuenta.route) })
            DrawerItem(icon = Icons.Default.Info, label = stringResource(R.string.drawer_menu_help), onClick = { navigate(Screen.Ayuda.route) })
            
            Spacer(modifier = Modifier.weight(1f))
            
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            DrawerItem(icon = Icons.Default.Settings, label = stringResource(R.string.drawer_menu_settings), onClick = { navigate(Screen.Configuracion.route) })
            DrawerItem(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                label = stringResource(R.string.cerrar_sesion),
                labelColor = MaterialTheme.colorScheme.secondary,
                iconTint = MaterialTheme.colorScheme.secondary,
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
    labelColor: Color = MaterialTheme.colorScheme.onBackground,
    iconTint: Color = MaterialTheme.colorScheme.placeholderColor(),
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
fun StatCard(modifier: Modifier = Modifier, label: String, value: String, isLoading: Boolean = false) {
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    Column(
        modifier = modifier
            .height(100.dp)
            .background(cardColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .then(if (isLoading) Modifier.shimmerEffect() else Modifier)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isLoading) {
            Text(
                text = label,
                style = TextStyle(fontSize = 12.sp, color = Color.White.copy(alpha = 0.9f), textAlign = TextAlign.Center)
            )
            Text(
                text = value,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal, color = Color.White, textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable
fun ActionItem(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit = {}) {
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(24.dp))
            }
            Column {
                Text(text = title, style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground))
                Text(text = subtitle, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun DashboardScreenPreview() {
    DomumtechTheme {
        val navController = androidx.navigation.compose.rememberNavController()
        DashboardScreen(navController = navController)
    }
}
