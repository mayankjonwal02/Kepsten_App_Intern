package com.example.kepstenapp1.android.USER_UI



import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kepstenapp1.android.navigation.screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@SuppressLint("UnrememberedMutableState")
@Composable
fun mylist(
    heading: String = "Heading",
//    mylistitems: List<String> = mutableListOf(),
    navHostController: NavHostController,
    context: Context

    ) {
    var mylistitems = remember { mutableStateListOf<String?>() }
    var filteredItems = remember { mutableStateListOf<String?>() }
    var tosearch by remember { mutableStateOf("") }

// Filter the list of items based on the search query
    if (tosearch.isNotBlank()) {
        filteredItems.clear()
        filteredItems.addAll(
            rememberUpdatedState(
                mylistitems.filter { it?.lowercase()?.startsWith(tosearch.lowercase()) == true }
                    .sortedBy { it?.lowercase() }
            ).value
        )
    } else {
        filteredItems = mylistitems
    }



    FirebaseDatabase.getInstance().reference.child("services").addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            if(snapshot.exists())
            {
                mylistitems.clear()
                 for( i in snapshot.children)
                 {
                     mylistitems.add(i.getKey().toString()!!)
                 }
            }
            else
            {
                Toast.makeText(context,"No Services",Toast.LENGTH_LONG).show()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
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
            Spacer(modifier = Modifier.height(40.dp))
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


            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn(
                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(items = filteredItems) { data ->
                    if (data != null) {
                        listitem(text = data ,navHostController= navHostController, heading = heading)
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun listitem(text: String = "Item ", heading: String, navHostController: NavHostController)
{

    var selected by  remember{
        mutableStateOf(false)

    }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { }
            .shadow(13.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Green)

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
                .clickable { selected = !selected
                           }
            ,
        )
        if (selected)
        {
            when(heading)
            {
                "Select Services" -> navHostController.navigate(screen.companies.route+"/${text}")
                "Select Brand" -> navHostController.navigate(screen.paymentdue.route)
            }

        }

    }
}



@Composable
fun lazycol(function: () -> Unit= {})
{
    var sections = mutableListOf("A","B","C","D","E","F")
    LazyColumn(contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp))
    {
        items(items = sections)
        {
                no -> listitem(no, navHostController = rememberNavController(), heading = "")
            //lazycollayout(no = no)
        }
    }

}

