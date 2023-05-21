package com.example.kepstenapp1.android

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.Card
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
import androidx.navigation.NavHostController
import com.example.kepstenapp1.android.navigation.screen


@Composable
fun way(navHostController: NavHostController, context: Context) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ,
//        contentAlignment = Alignment.TopCenter
    )

    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
        ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Using App as", modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center, fontStyle = FontStyle.Normal, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.ExtraBold, fontSize = 40.sp))
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                Modifier
                    .fillMaxSize()
//                    .weight(1f)
            ) {
                
                Card(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable{
                              navHostController.navigate(screen.signinscreen.route+"/${"Organisation"}/${"1"}")
                    }
                    ,
                    shape = RoundedCornerShape(20.dp)
                    ,

                backgroundColor = Color.Green) {
                    
                    Text(modifier = Modifier.padding(10.dp), text = "Organisation", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    
                }
                Spacer(modifier = Modifier.height(30.dp))
                Card(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable{
                        navHostController.navigate(screen.signinscreen.route+"/${"Customer"}/${"1"}")
                    },

                    shape = RoundedCornerShape(20.dp),

                    backgroundColor = Color.Green) {

                    Text(modifier = Modifier.padding(10.dp), text = "Customer", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
                Spacer(modifier = Modifier.height(30.dp))
                Card(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable{
                        navHostController.navigate(screen.signinscreen.route+"/${"Worker"}/${"0"}")
                    },

                    shape = RoundedCornerShape(20.dp)
                            ,
                    backgroundColor = Color.Green) {

                    Text(modifier = Modifier.padding(10.dp), text = "Worker", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
                
            }
            
        }

    }

}