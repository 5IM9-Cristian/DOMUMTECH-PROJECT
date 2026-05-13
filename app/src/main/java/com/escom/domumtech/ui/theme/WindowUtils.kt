package com.escom.domumtech.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun SetupEdgeToEdge(
    persistentColor: Color = MaterialTheme.colorScheme.primary,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    // Vacío para evitar errores en Preview
}

@Composable
fun ApplyAppSystemUi(
    persistentColor: Color = MaterialTheme.colorScheme.primary,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    // Nueva función para evitar cache de Preview
}

fun Context.findActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    return null
}
