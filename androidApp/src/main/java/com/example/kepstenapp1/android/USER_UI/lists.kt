package com.example.kepstenapp1.android.USER_UI



import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
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
fun mylist(
    heading: String = "Heading",
    mylist: MutableList<String> = mutableListOf("one", "two", "three", "four")
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxHeight()
//                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn(
                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(items = mylist) { data ->
                    listitem(data)
                }
            }
        }
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun listitem(text: String = "Item ")
{

    var selected by  remember{
        mutableStateOf(false)

    }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .shadow(13.dp, shape = RoundedCornerShape(20.dp),spotColor = Color.Green)

            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected = true
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        selected = false
                        true
                    }
                    else -> false
                }
            }

        // .clickable { selected=!selected }
        ,
        backgroundColor =  if (selected) {
            Color.Green
        } else {
            Color.White
        }

    ) {

        Text(text = text , style = TextStyle( color = Color.Black , fontSize = 30.sp, textAlign = TextAlign.Center) ,
            modifier = Modifier
                .padding(10.dp)
                .clickable { selected = !selected }
            ,
        )

    }
}


@Preview
@Composable
fun lazycol()
{
    var sections = mutableListOf("A","B","C","D","E","F")
    LazyColumn(contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp))
    {
        items(items = sections)
        {
                no -> listitem(no)
            //lazycollayout(no = no)
        }
    }

}

