package com.example.kepstenapp1.android.UI

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

//@Preview
@Composable
fun mylist(heading:String = "Heading")
{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White))
    {

        Column(modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .align(Alignment.TopCenter)
            ) {

            Text(text = heading ,

                modifier = Modifier.padding(20.dp)
                    ,
            style = TextStyle(fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Monospace)
            )
            
        }
    }
}

@Preview
@Composable
fun listitem(text:String = "Item ")
{
    var selected by  remember{
        mutableStateOf(false)

    }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
        .wrapContentHeight()
            .fillMaxWidth()

        .clickable { selected=!selected },
        backgroundColor =  if (selected) {
            Color.Green
        } else {
            Color.White
        }

    ) {

        Text(text = text , style = TextStyle( color = if (selected) {
            Color.Green
        } else {
            Color.Gray
        }, fontSize = 30.sp, textAlign = TextAlign.Center) ,
        modifier = Modifier.padding(10.dp).clickable { selected=!selected }
        ,
        )

    }
}