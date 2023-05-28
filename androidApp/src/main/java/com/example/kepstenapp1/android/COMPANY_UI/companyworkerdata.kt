package com.example.kepstenapp1.android.COMPANY_UI

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kepstenapp1.android.USER_UI.twoitem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class companyworkerdata(

                       var password: String = "not alloted",
                       var workername: String = "not applicable",
                       var workercno: String = " sdidc",
                       var workerid: String = "yes",
                       var locality: String = "ghaziabad",
                       var state : String = "Uttar Pradesh",
                       var address : String = "full Address"

                       )

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun companyworkerdatalist() {
    var context = LocalContext.current
    var datalist = remember { mutableStateListOf<companyworkerdata>() }
    var filteredItems = remember { mutableStateListOf<companyworkerdata>() }
    var expanded by remember { mutableStateOf(false) }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {

        FirebaseDatabase.getInstance().reference.child("workers").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (i in snapshot.children) {

                    if(i.child("companyuid").value.toString() == FirebaseAuth.getInstance().currentUser?.uid.toString()) {

                        var data = companyworkerdata()
                        data.workername = i.child("name").value.toString()
                        data.password = i.child("password").value.toString()
                        data.workercno = i.child("contact").value.toString()
                        data.workerid = i.key.toString()
                        data.locality = i.child("locality").value.toString()
                        data.state = i.child("state").value.toString()
                        data.address = i.child("address").value.toString()



                        datalist.add(data)





                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        var tosearch by remember { mutableStateOf("") }

// Filter the list of items based on the search query
        if (tosearch.isNotBlank()) {
            filteredItems.clear()
            filteredItems.addAll(
                rememberUpdatedState(
                    datalist.filter { it?.workername?.lowercase()?.startsWith(tosearch.lowercase()) == true }
                        .sortedBy { it?.workername?.lowercase() }
                ).value
            )
        } else {
            filteredItems = datalist
        }




        Column(Modifier.fillMaxSize())
        {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), textAlign = TextAlign.Center, text = "Worker's Data", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal)

            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                value = tosearch,
                onValueChange = { tosearch = it  },
                label = { Text("Search ") },
//            placeholder = { Text("Enter your Address") },
                modifier = Modifier
                    .fillMaxWidth().padding(20.dp)
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
            LazyColumn(

                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),

                ) {

                items(items =filteredItems) { data ->
                    Card(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .clickable { expanded = !expanded },
                        backgroundColor = Color.Green,
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Column(modifier = Modifier

                            .padding(10.dp),

                            ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Worker Name: ${data.workername}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Worker Cno.: ${data.workercno}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            AnimatedVisibility(visible = expanded) {
                                if (expanded) {
                                    Column {
                                        Text(
                                            text = "Worker ID.: ${data.workerid}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(

                                            text = "Password: ${data.password}",
                                            fontSize = 16.sp
                                            ,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(

                                            text = "Locality: ${data.locality}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(

                                            text = "State: ${data.state}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(

                                            text = "Address: ${data.address}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }
                            Icon(
                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Expand/Collapse Icon",
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .clickable { expanded = !expanded }
                                    .padding(top = 8.dp)
                                    .size(24.dp)
                            )
                        }
                    }

                }



            }
        }
    }
}
