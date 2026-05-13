package com.escom.domumtech.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ColorScheme.escaner():Color = if(isSystemInDarkTheme()) CiderSpice else EscanerColor
@Composable
fun ColorScheme.cardsColor():Color= if (isSystemInDarkTheme()) Color.DarkGray else Color.White
@Composable
fun ColorScheme.dynamicGradient(): Brush {
    return if (isSystemInDarkTheme()) GradientDark else GradientLight
}
@Composable
fun ColorScheme.placeholderColor():Color = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray

@Composable
fun ColorScheme.usrMessages():Color = if (isSystemInDarkTheme()) Color(0xFF574F4F) else Color(0xFFFFF9F2)
@Composable
fun ColorScheme.virtualAssistenMessages():Color = if (isSystemInDarkTheme()) Color.Gray else Color.White

private val DarkColorScheme = darkColorScheme(
    primary = BarnRed,
    secondary = PottersClay,
    tertiary = BloodRed,
    background = BackgroundDark,
    surface = Color(0xFF737373),
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Terracotta,
    secondary = OrangeGradientEnd,
    tertiary = AnalogousPink,
    background = BackgroundLight,
    surface = Color(0xFFF0E9DA),
    onBackground = Color.Black,
)

@Composable
fun DomumtechTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    val activity = view.context.findActivity()
    
    if (activity != null) {
        SideEffect {
            val window = activity.window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
