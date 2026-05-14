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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.ui.theme.DomumtechTheme
import com.escom.domumtech.ui.theme.cardsColor
import com.escom.domumtech.ui.theme.dynamicGradient
import com.escom.domumtech.ui.theme.placeholderColor

@Composable
fun CambioPasswordScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val mainGradient = MaterialTheme.colorScheme.dynamicGradient()
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainGradient)
                .statusBarsPadding()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Cambiar contraseña",
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
                    .background(backgroundColor, RoundedCornerShape(14.dp))
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Por tu seguridad, asegúrate de crear una contraseña fuerte que cumpla con todos los requisitos.",
                    style = TextStyle(fontSize = 14.sp, lineHeight = 22.sp, color = onBackgroundColor)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Form Fields
            PasswordTextField(
                label = "Contraseña actual",
                value = currentPassword,
                onValueChange = { currentPassword = it },
                placeholder = "Ingresa tu contraseña actual",
                isVisible = currentPasswordVisible,
                onVisibilityChange = { currentPasswordVisible = !currentPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                label = "Nueva contraseña",
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = "Ingresa tu nueva contraseña",
                isVisible = newPasswordVisible,
                onVisibilityChange = { newPasswordVisible = !newPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                label = "Confirmar nueva contraseña",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirma tu nueva contraseña",
                isVisible = confirmPasswordVisible,
                onVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Update Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(14.dp),
                enabled = currentPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        if (currentPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()) mainGradient 
                        else Brush.linearGradient(listOf(placeholderColor, placeholderColor))
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Actualizar contraseña",
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
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.placeholderColor()
    val cardColor = MaterialTheme.colorScheme.cardsColor()

    Column {
        Text(
            text = label,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = onBackgroundColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = placeholderColor) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = placeholderColor) },
            trailingIcon = {
                IconButton(onClick = onVisibilityChange) {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Info else Icons.Default.Lock,
                        contentDescription = null,
                        tint = placeholderColor
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = placeholderColor.copy(alpha = 0.5f),
                unfocusedContainerColor = cardColor,
                focusedContainerColor = cardColor
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CambioPasswordScreenPreview() {
    DomumtechTheme {
        CambioPasswordScreen(navController = rememberNavController())
    }
}
