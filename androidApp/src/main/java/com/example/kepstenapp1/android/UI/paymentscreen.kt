package com.example.kepstenapp1.android.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview
//@Composable
//fun payment(company:String = "Company name",service:String="Service name",amount:Double=1000.0) {
//
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(color = Color.Green))
//    {
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 30.dp)
//            //.wrapContentHeight()
//            //.align(Alignment.TopCenter)
//
//            ,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        )
//        {
//            Text(
//                text = "Payment",
//                style = TextStyle(
//                    fontSize = 70.sp,
//                    color = Color.White,
//                    fontFamily = FontFamily.Cursive,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.ExtraBold,
//                    textAlign = TextAlign.Left
//                )
//            )
//        }
//    }
//
//}


@Preview
@Composable
fun payment(company:String = "Company name",service:String="Service name",amount:Double=1000.0) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green))
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.Start,
        )
        {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Payment",
                style = TextStyle(
                    fontSize = 70.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
