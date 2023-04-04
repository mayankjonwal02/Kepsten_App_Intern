package com.example.kepstenapp1.android.COMPANY_UI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kepstenapp1.android.USER_UI.listitem

@Composable
fun SearchScreen(heading:String = "Heading") {
    val items = listOf(
        "Apple", "Banana", "Cherry", "Date", "Elderberry",
        "Fig", "Grape", "Honeydew", "Jackfruit", "Kiwi"
    )
    val filteredItems:List<String>
    var tosearch by remember { mutableStateOf("")}

    // Filter the list of items based on the search query

    if (tosearch  != "")
    {
        filteredItems = items.filter { it.lowercase().startsWith(tosearch.lowercase()) }
            .sortedBy { it.lowercase() }

    }
    else
    {
        filteredItems = listOf<String>()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = heading, modifier = Modifier.fillMaxWidth().padding(20.dp)
            , style = TextStyle(fontStyle = FontStyle.Normal, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Monospace, fontSize = 40.sp))
        // Search bar
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = tosearch,
            onValueChange = { tosearch = it  },
            label = { Text("Search ") },
//            placeholder = { Text("Enter your Address") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(9.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent

            )
        ,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon ")

                }
            }

        )

        // Elements below the search bar
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Recent searches")
        // Add more composables here for other elements below the search bar
        Spacer(modifier = Modifier.height(70.dp))
        LazyColumn(modifier = Modifier.padding(16.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(40.dp),) {
            items(filteredItems) { item ->
                listitem(item)
            }
        }
    }
}
