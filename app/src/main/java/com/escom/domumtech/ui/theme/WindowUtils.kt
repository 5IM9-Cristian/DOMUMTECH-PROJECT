package com.escom.domumtech.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.runtime.SideEffect
import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


@Composable
fun SetupEdgeToEdge(
    // Por defecto usa el color primario del tema para restaurar
    persistentColor: Color = MaterialTheme.colorScheme.primary, //Color transparent funciona para algunas
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val view = LocalView.current
    val context = view.context as Activity


    DisposableEffect(Unit) {
        val window = context.window
        val insetsController = WindowCompat.getInsetsController(window, view)

        window.statusBarColor = Color.Transparent.toArgb()
        insetsController.isAppearanceLightStatusBars = !darkTheme

        onDispose {
            window.statusBarColor = persistentColor.toArgb()
        }
    }
}

    /*if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
        }
    }*/
