package com.example.kepstenapp1.android.USER_UI



import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.background
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
import com.example.kepstenapp1.android.navigation.screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class twoitem(val companyname: String, val cost: Double)


@SuppressLint("UnrememberedMutableState")
@Composable
fun my2itemlist(
    heading: String = "Heading",

    service: String,
    navHostController: NavHostController

) {
    var mylist = remember { mutableStateListOf<twoitem?>() }


    var filteredItems = remember { mutableStateListOf<twoitem?>() }
    var tosearch by remember { mutableStateOf("") }

// Filter the list of items based on the search query
    if (tosearch.isNotBlank()) {
        filteredItems.clear()
        filteredItems.addAll(
            rememberUpdatedState(
                mylist.filter { it?.companyname?.lowercase()?.startsWith(tosearch.lowercase()) == true }
                    .sortedBy { it?.companyname?.lowercase() }
            ).value
        )
    } else {
        filteredItems = mylist
    }


    FirebaseDatabase.getInstance().reference.child("services").child(service).addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            mylist.clear()

            for ( i in snapshot.children)
            {
                var a = twoitem(i.key.toString(),i.value.toString().toDouble())
                mylist.add(a)
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

            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = heading,
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace
                )
            )
            val a = mutableListOf("1","2","3","4")
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
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(

                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),

            ) {
                items(items = filteredItems) {
                        data ->
                    if (data != null) {
                        twolistitem(data = data, service = service,navHostController)
                    }

                }
            }
        }
    }
}




@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun twolistitem(
    data: twoitem = twoitem("mayank", 1254.6),
    service: String,
    navHostController: NavHostController
)
{

    var selected by  remember{
        mutableStateOf(false)

    }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Element at the start of the row
            Text(
                text = data.companyname,
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                fontWeight = FontWeight.ExtraBold
            )

            // Element at the end of the row
            Text(
                text = "Price : "+data.cost.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                textAlign = TextAlign.End
            )

            if (selected)
            {
                navHostController.navigate(screen.paymentdue.route+"/${service}/${data.companyname}/${data.cost.toString()}")
            }
        }


    }
}
