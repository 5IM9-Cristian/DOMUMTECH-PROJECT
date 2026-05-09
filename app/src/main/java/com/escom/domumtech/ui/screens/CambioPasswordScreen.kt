package com.escom.domumtech.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CambioPasswordScreen() {
    val scrollState = rememberScrollState()
    val mainGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFDC7176), Color(0xFFF2A666))
    )

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient)
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Cambiar Contraseña",
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                        )
                        Text(
                            text = "Actualiza tu contraseña de acceso",
                            style = TextStyle(fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Info Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F2), RoundedCornerShape(14.dp))
                    .border(1.5.dp, Color(0xFFCDD7D8).copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Por tu seguridad, asegúrate de crear una contraseña fuerte que cumpla con todos los requisitos.",
                    style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = Color(0xFF1A1A1A))
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Form Fields
            PasswordTextField(
                label = "Contraseña Actual",
                value = currentPassword,
                onValueChange = { currentPassword = it },
                placeholder = "Ingresa tu contraseña actual",
                isVisible = currentPasswordVisible,
                onVisibilityChange = { currentPasswordVisible = !currentPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                label = "Nueva Contraseña",
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = "Ingresa tu nueva contraseña",
                isVisible = newPasswordVisible,
                onVisibilityChange = { newPasswordVisible = !newPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                label = "Confirmar Nueva Contraseña",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirma tu nueva contraseña",
                isVisible = confirmPasswordVisible,
                onVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Update Button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(mainGradient),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Actualizar Contraseña",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PasswordTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        Text(
            text = label,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A1A1A))
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFFCDD7D8)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.LightGray.copy(alpha = 0.5f)) },
            trailingIcon = {
                IconButton(onClick = onVisibilityChange) {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Lock else Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.LightGray.copy(alpha = 0.5f)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFDC7176).copy(alpha = 0.5f),
                unfocusedBorderColor = Color(0x4DCDD7D8)
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 853)
@Composable
fun CambioPasswordScreenPreview() {
    CambioPasswordScreen()
}
