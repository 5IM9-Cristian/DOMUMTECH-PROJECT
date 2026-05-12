package com.escom.domumtech.ui.screens
import com.escom.domumtech.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.escom.domumtech.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.escom.domumtech.ui.theme.DomumtechTheme

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.inicio_sesion),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                //Clic en el campo
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                //Cuando no se le ha dado clic al campo
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                //Cuando no se selecciona
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                //Color de la etiqueta
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                //color del cursor
                cursorColor = MaterialTheme.colorScheme.tertiary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                //Clic en el campo
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                //Cuando no se le ha dado clic al campo
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                //Cuando no se selecciona
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                //Color de la etiqueta
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                //color del cursor
                cursorColor = MaterialTheme.colorScheme.tertiary
            )
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { navController.navigate(Screen.Dashboard.route) },
            modifier = Modifier.fillMaxWidth().height(56.dp).shadow(4.dp, RoundedCornerShape(14.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(stringResource(R.string.entrar), color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    DomumtechTheme() {
        LoginScreen(navController = rememberNavController())
    }
}
