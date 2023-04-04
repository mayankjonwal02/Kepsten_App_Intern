package com.example.kepstenapp1.android.USER_UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kepstenapp1.android.R

@Composable
fun image() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))

    {
        Image(painter = painterResource(id = R.drawable.google_logoq), contentDescription = null )
    }
    
    
}


@Preview
@Composable
fun experiemntpreview() {
    
    image()
    
}