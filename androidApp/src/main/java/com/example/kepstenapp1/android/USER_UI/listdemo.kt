package com.example.kepstenapp1.android.USER_UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Preview
@Composable
fun mysecondlist(heading:String = "Heading") {

//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(Color.White))
//    {
//
//        Column(modifier = Modifier
//            .padding(15.dp)
//            .verticalScroll(rememberScrollState())
//            .fillMaxSize()) {
//
//            Text(
//                text = heading,
//                modifier = Modifier
//                    .padding(20.dp)
//                    .fillMaxWidth(),
//                style = TextStyle(
//                    fontWeight = FontWeight.ExtraBold,
//                    textAlign = TextAlign.Center,
//                    fontSize = 30.sp,
//                    fontStyle = FontStyle.Normal,
//                    fontFamily = FontFamily.Monospace
//                )
//            )
//            Spacer(modifier = Modifier.height(30.dp))
//            val sections = mutableListOf("A","B","C","D","E","F")
//            val items = (0..100).toList()
//            mydata()
//
//
//
//        }
//    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Column(
            modifier = Modifier
//                .padding(all = 15.dp)
                .fillMaxHeight()
//                .verticalScroll(rememberScrollState())
            ,
//            horizontalAlignment = Alignment.CenterHorizontally
        )  {
            Text(
                text = heading,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace
                )
            )
            Spacer(modifier = Modifier.height(80.dp))
            val sections = mutableListOf("A","B","C","D","E","F")
            val items = (0..100).toList()

            LazyColumn(contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(sections) { item ->
                    listitem(item)
                }
            }

        }

    }
}


@Composable
fun mydata() {
    val sections = mutableListOf("A","B","C","D","E","F")
    val items = (0..100).toList()

    LazyColumn(contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(sections) { item ->
            listitem(item)
        }
    }

}
