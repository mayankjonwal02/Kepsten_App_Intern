package com.example.kepstenapp1.android.COMPANY_UI


import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun addslots() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {

        Text(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .padding(40.dp), text = "Slots", style = TextStyle(fontStyle = FontStyle.Normal, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.ExtraBold, fontSize = 50.sp, textAlign = TextAlign.Center))
            
            
        Row(modifier = Modifier.wrapContentSize()) {
            
        }

    }


}


