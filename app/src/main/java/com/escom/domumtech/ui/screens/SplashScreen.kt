package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Placeholder para el Logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Domumtech",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDC7176)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Powered by Unfly",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
