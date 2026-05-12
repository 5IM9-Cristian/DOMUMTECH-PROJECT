package com.escom.domumtech.ui.theme

import androidx.compose.material3.CardColors
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//Colores usados para lightmode
val Terracotta = Color(0xFFDC7176)
val OrangeGradientEnd = Color(0xFFF2A666)
val AnalogousPink = Color(0xFFDC71AC)
val BackgroundLight = Color(0xFFFFF9F2)

val EscanerColor = Color(0xFFFFF9F2)

//Colores usados para darkmode
val BarnRed = Color(0xFF761305)
//val OverBaked = Color(0xFF7A3C26)
val PottersClay = Color(0xFFA14422)
val BloodRed = Color(0xFF550F05)
val BackgroundDark = Color(0xFF121212)

val CiderSpice = Color(0xFFB58C6F).copy(alpha = 0.5f)

//Gradientes
val GradientLight = Brush.horizontalGradient(
    colors = listOf(Terracotta, OrangeGradientEnd)
)

val GradientDark = Brush.horizontalGradient(
    colors = listOf(BloodRed, PottersClay)
)

