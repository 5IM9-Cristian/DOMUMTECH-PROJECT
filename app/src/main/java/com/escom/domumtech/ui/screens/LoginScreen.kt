package com.escom.domumtech.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.R
import com.escom.domumtech.navigation.Screen
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val secondaryTextColor = MaterialTheme.colorScheme.secondary
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val cardColor = MaterialTheme.colorScheme.cardsColor()
    
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Cabecera con Gradiente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(mainGradient)
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.inicio_sesion),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Contenido del Formulario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Bienvenido de nuevo",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = onBackgroundColor,
                )
            )
            Text(
                text = "Ingresa tus credenciales para continuar",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = secondaryTextColor,
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Campo Correo
            Text(
                text = "Correo Electrónico",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = onBackgroundColor)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("tu@email.com", color = MaterialTheme.colorScheme.placeholderColor()) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = cardColor,
                    focusedContainerColor = cardColor,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.placeholderColor().copy(alpha = 0.5f)
                ),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = secondaryTextColor.copy(alpha = 0.7f))
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo Contraseña
            Text(
                text = "Contraseña",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = onBackgroundColor)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("••••••••", color = MaterialTheme.colorScheme.placeholderColor()) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = cardColor,
                    focusedContainerColor = cardColor,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.placeholderColor().copy(alpha = 0.5f)
                ),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = secondaryTextColor.copy(alpha = 0.7f))
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = onBackgroundColor,
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón Iniciar Sesión
            Button(
                onClick = { navController.navigate(Screen.Dashboard.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                enabled = email.isNotBlank() && password.isNotBlank()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (email.isNotBlank() && password.isNotBlank()) mainGradient else Brush.linearGradient(listOf(Color.LightGray, Color.LightGray))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Divisor "O continúa con"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.placeholderColor().copy(alpha = 0.5f))
                Text(
                    text = "O continúa con",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = TextStyle(fontSize = 14.sp, color = secondaryTextColor)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.placeholderColor().copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Google
            OutlinedButton(
                onClick = { /* Lógica de Google */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.placeholderColor().copy(alpha = 0.5f)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = cardColor)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Simulación Icono Google
                    Box(
                        modifier = Modifier
                            .size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.google_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Continuar con Google",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DomumtechTheme {
        LoginScreen(navController = rememberNavController())
    }
}
