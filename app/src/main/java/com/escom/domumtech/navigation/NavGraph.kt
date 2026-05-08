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
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) { SplashScreen() }
        composable(Screen.Welcome.route) { WelcomeScreen() }
        composable(Screen.Login.route) { LoginScreen() }
        composable(Screen.Registro.route) { RegistroScreen() }
        composable(Screen.Dashboard.route) { DashboardScreen() }
    }
}
