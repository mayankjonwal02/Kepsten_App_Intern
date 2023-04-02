package com.example.kepstenapp.android.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kepstenapp1.android.R


@androidx.compose.runtime.Composable
fun splashscreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ,


        )
    {
        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(10.dp)
        ,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                   Image(painter = painterResource(id = R.drawable.kepsten_icon), contentDescription = "", modifier = Modifier.size(500.dp))
                    //Text(text = "Kepsten", fontSize = 30.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.SansSerif)

        }

    }

}


@androidx.compose.ui.tooling.preview.Preview
@androidx.compose.runtime.Composable
fun splashscreenpreview() {
    splashscreen()
}