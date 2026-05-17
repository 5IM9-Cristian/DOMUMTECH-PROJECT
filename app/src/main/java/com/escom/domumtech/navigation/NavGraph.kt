package com.escom.domumtech.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escom.domumtech.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Registro : Screen("registro")
    object Dashboard : Screen("dashboard")
    object AltaProducto : Screen("alta_producto")
    object BajaProducto : Screen("baja_producto")
    object InventarioCompartido : Screen("inventario_compartido")
    object ListaCompras : Screen("lista_compras")
    object AlmacenistaChat : Screen("almacenista_chat")
    object MiCuenta : Screen("mi_cuenta")
    object MiembrosPlan : Screen("miembros_plan")
    object PlanesMembresia : Screen("planes_membresia")
    object Configuracion : Screen("configuracion")
    object CambioPassword : Screen("cambio_password")
    object GestionDatos : Screen("gestion_datos")
    object Ayuda : Screen("ayuda")
    object TerminosCondiciones : Screen("terminos_condiciones")
    object PoliticaPrivacidad : Screen("politica_privacidad")
    object NotaCompras : Screen("nota_compras")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Registro.route) { RegistroScreen(navController) }
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable(Screen.AltaProducto.route) { AltaProductoScreen(navController) }
        composable(Screen.BajaProducto.route) { BajaProductoScreen(navController) }
        composable(Screen.InventarioCompartido.route) { InventarioCompartidoScreen(navController) }
        composable(Screen.ListaCompras.route) { ListaComprasScreen(navController) }
        composable(Screen.AlmacenistaChat.route) { AlmacenistaChatScreen(navController) }
        composable(Screen.MiCuenta.route) { MiCuentaScreen(navController) }
        composable(Screen.MiembrosPlan.route) { MiembrosPlanScreen(navController) }
        composable(Screen.PlanesMembresia.route) { PlanesMembresiaScreen(navController) }
        composable(Screen.Configuracion.route) { ConfiguracionScreen(navController) }
        composable(Screen.CambioPassword.route) { CambioPasswordScreen(navController) }
        composable(Screen.GestionDatos.route) { GestionDatosScreen(navController) }
        composable(Screen.Ayuda.route) { AyudaScreen(navController) }
        composable(Screen.TerminosCondiciones.route) { TerminosCondicionesScreen(navController) }
        composable(Screen.PoliticaPrivacidad.route) { PoliticaPrivacidadScreen(navController) }
        composable(Screen.NotaCompras.route) { NotaComprasScreen(navController) }
    }
}

//Para regresar el color de la barra en caso que sea necesario

/*
navController.addOnDestinationChangedListener { _, destination, _ ->
    val window = (context as Activity).window
    when (destination.route) {
        Screen.Registro.route -> {
            window.statusBarColor = Color.Transparent.toArgb()
        }
        else -> {
            window.statusBarColor = colorScheme.primary.toArgb()
        }
    }
} */
