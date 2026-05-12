package com.escom.domumtech.ui.legacy
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.escom.domumtech.R

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.tooling.preview.Preview
import com.escom.domumtech.ui.theme.DomumtechTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import com.escom.domumtech.ui.theme.dynamicGradient

@Composable
fun Welcome(){

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.dynamicGradient()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(1f))

        LogoImageWelcome()

        Spacer(modifier = Modifier.weight(0.6f))

        BotonesDeSesion()
    }
}

@Composable
fun LogoImageWelcome(){
    val image = painterResource(if (isSystemInDarkTheme()) R.drawable.domumtech_logo_naranja else R.drawable.domumtech_logo)
    Column(
        modifier = Modifier.padding(bottom= 32.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = image,
            contentDescription = "Imagen de Domumtech",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(width = 200.dp, height = 180.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 4.dp,
                    color = Color(0xFFE07970),
                    shape = RoundedCornerShape(20.dp)
                )
        )
        Text(
            text = stringResource(R.string.domumtech_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White
        )
        Text(
            text = "texto",
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding( 16.dp),
            color = Color.White
        )
    }
}

@Composable
fun BotonesDeSesion(){
    Column(
        modifier = Modifier.padding(bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp)
                .border(
                    width = 5.dp,
                    color = Color(0xFFE07970),
                    shape = RoundedCornerShape(20.dp)
                ),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFFE07970)
            )
        ) {
            Text(
                text = stringResource(R.string.ingresar),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = stringResource(R.string.registrarse),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview(){
    DomumtechTheme {
        Welcome()
    }
}
