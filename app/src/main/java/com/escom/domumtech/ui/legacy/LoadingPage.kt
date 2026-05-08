package com.example.domumtech.ui
import com.example.domumtech.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.domumtech.ui.theme.DomumtechTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface


@Composable //Funcion principal que contiene el contenido de la pantalla
fun LoadingPage(){
    Surface( //Se utiliza el Surface para cambiar el color de fondo de pantalla
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ){
        Column( //Dentro de este contenedor se usa una columna para repartir el espacio entre los elementos de espacio para centrar y el que muestra el logo
            modifier = Modifier.fillMaxSize().padding(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LogoImage()
        }
    }
}

@Composable
fun LogoImage(){
    val image = painterResource(R.drawable.domumtech_logo)
    Column(
        modifier = Modifier.padding(0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .border(
                    width = 10.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
        )
        Text(
            text = stringResource(R.string.loading_message),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingPagePreview() {
    DomumtechTheme {
        LoadingPage()
    }
}