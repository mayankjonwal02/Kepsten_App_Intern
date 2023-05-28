package com.example.kepstenapp1.android.workerui

import android.content.Context
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun Workerinfo(context: Context,workeruid : String = "zafaraiyar") {

    var name by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser?.email.toString())
    }

    var locality by remember {
        mutableStateOf("")
    }

    var state by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }
    var databaseref = FirebaseDatabase.getInstance().reference.child("workers").child(workeruid)

    val addValueEventListener = databaseref.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            name = snapshot.child("name").value.toString()
            contact = snapshot.child("contact").value.toString()
            email = workeruid
            locality = snapshot.child("locality").value.toString()
            state = snapshot.child("state").value.toString()
            address = snapshot.child("address").value.toString()
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context,error.message.toString(),Toast.LENGTH_LONG).show()
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )

    {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(top = 60.dp, bottom = 80.dp)
        ) {
            //Spacer(modifier = Modifier.height(50.dp))
//            Text(
//                text = "USER INFO",
//                modifier = Modifier.fillMaxWidth(),
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 40.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    fontStyle = FontStyle.Normal,
//                    fontFamily = FontFamily.Cursive,
//                    textAlign = TextAlign.Center
//                )
//            )
            Spacer(modifier = Modifier.height(80.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = name,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text("User Name") },
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Face, contentDescription = " icon")

                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    placeholder = { Text("Enter your Name") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    ),

                    )




            }
            // add here
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = contact,
                    onValueChange = {contact = it},

                    label = { Text("Contact Number") },
                    placeholder = { Text("Enter your contact number") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Phone, contentDescription = " icon")

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    )
                )
            }
            // #########

            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = email,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text("Email") },
                    placeholder = { Text("Enter email address") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    ),
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Email, contentDescription = " icon")

                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = locality,
                    onValueChange = { locality = it },
                    label = { Text("Locality") },
                    placeholder = { Text("Enter your Locality") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Place, contentDescription = " icon")

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    )
                )
            }



            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = state,
                    onValueChange = {state = it },
                    label = { Text("State") },
                    placeholder = { Text("Enter your State") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Place, contentDescription = " icon")

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    )
                )
            }


            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = address,
                    onValueChange = {address = it },
                    label = { Text("Full Address") },
                    placeholder = { Text("Enter your Address") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = " icon")

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.Green
                    )
                )
            }
        }

        // update button
        Text(
            text = "Worker's Info",
            modifier = Modifier

                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(50.dp)
                .background(Color.White)
            ,
            style = TextStyle(
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center
            )
        )


        var text by remember { mutableStateOf("Update") }

        OutlinedButton(
            onClick = {
                if(name.isNotBlank() && contact.isNotBlank() && email.isNotBlank() && locality.isNotBlank() && state.isNotBlank() && address.isNotBlank())
                {
                    text = "Save"
                    var data = workerselfdata(name, contact.toString(), email, locality, state, address)
                    FirebaseDatabase.getInstance().reference.child("workers")
                        .child(workeruid).child("state")
                        .setValue(data.state)
                    FirebaseDatabase.getInstance().reference.child("workers")
                        .child(workeruid).child("locality")
                        .setValue(data.locality)
                    FirebaseDatabase.getInstance().reference.child("workers")
                        .child(workeruid).child("address")
                        .setValue(data.address)
                    Toast.makeText(context,"Data Updated",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(context,"Fields Empty",Toast.LENGTH_LONG).show()
                }}
            ,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = text,
                color = Color.Red,

                style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal)
            )
        }




    }
}

data class workerselfdata(var name : String , var contact : String , var email : String , var locality : String , var state : String , var address : String )
