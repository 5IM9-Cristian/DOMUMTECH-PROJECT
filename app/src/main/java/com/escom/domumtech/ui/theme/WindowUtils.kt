package com.escom.domumtech.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetupEdgeToEdge(
    persistentColor: Color = MaterialTheme.colorScheme.primary,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val isPreview = LocalInspectionMode.current
    if (isPreview) return

    val view = LocalView.current
    val context = LocalContext.current
    val activity = context.findActivity()

    if (activity != null) {
        DisposableEffect(Unit) {
            val window = activity.window
            val insetsController = WindowCompat.getInsetsController(window, view)

            window.statusBarColor = Color.Transparent.toArgb()
            insetsController.isAppearanceLightStatusBars = !darkTheme

            onDispose {
                window.statusBarColor = persistentColor.toArgb()
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    return null
}
