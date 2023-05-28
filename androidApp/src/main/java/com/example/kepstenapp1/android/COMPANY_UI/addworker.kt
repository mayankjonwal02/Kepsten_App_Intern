package com.example.kepstenapp1.android.COMPANY_UI

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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


@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun addworker() {
    var context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }
     var uid by remember {
         mutableStateOf("")
     }
    var contact by remember{
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .alpha(0.3f)
            .fillMaxSize()
            .background(Color.Green)
    )
    Card(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
            .alpha(1f)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            Text(text = "Worker's Section", fontSize = 45.sp, modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Cursive, fontStyle = FontStyle.Normal))
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),

                placeholder = { Text(text = "Enter worker's Name") },
                label = { Text(text = "Worker's Name") },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.White,
                    backgroundColor = Color.White

                )
            )
            // copy from here
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = uid
                ,
                onValueChange = {  uid = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),

                placeholder = { Text(text = "Enter worker's UID") },
                label = { Text(text = "Worker UID") },


                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.White,
                    backgroundColor = Color.White

                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = contact,
                onValueChange = { contact = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),

                placeholder = { Text(text = "Enter worker's Contact no.") },
                label = { Text(text = "Worker's Contact No.") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.White,
                    backgroundColor = Color.White

                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.White),

                placeholder = { Text(text = "Enter worker's Password") },
                label = { Text(text = "Worker's Password") },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.White,
                    backgroundColor = Color.White

                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    uid = uid.replace("\\s".toRegex(), "")
                          if(name.isNotBlank() && uid.replace("\\s".toRegex(), "").isNotBlank() && contact.isNotBlank() && password.isNotBlank())
                          {

                              FirebaseDatabase.getInstance().reference.child("workers").child(uid.replace("\\s".toRegex(), "")).addListenerForSingleValueEvent(object :ValueEventListener{
                                  override fun onDataChange(snapshot: DataSnapshot) {
                                      if(!snapshot.exists())
                                      {
                                          FirebaseDatabase.getInstance().reference.child("workers").child(uid).setValue(workerdata(name, contact,password,FirebaseAuth.getInstance().currentUser?.uid.toString()))
                                          Toast.makeText(context,"$name added",Toast.LENGTH_LONG).show()
                                      }
                                      else{
                                          Toast.makeText(context,"$uid already exist",Toast.LENGTH_LONG).show()
                                      }
                                  }

                                  override fun onCancelled(error: DatabaseError) {
                                      Toast.makeText(context, "Database error: ${error.message}", Toast.LENGTH_LONG).show()

                                  }

                              })

                               }
                    else
                          {
                              Toast.makeText(context ,"Fields Empty", Toast.LENGTH_LONG ).show()

                          }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Magenta,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Add new Worker")
            }

            Button(
                onClick = {
                    uid = uid.replace("\\s".toRegex(), "")
                    if(name.isNotBlank() && uid.replace("\\s".toRegex(), "").isNotBlank() && contact.isNotBlank() && password.isNotBlank()) {

                        FirebaseDatabase.getInstance().reference.child("workers").child(uid.replace("\\s".toRegex(), "")).addListenerForSingleValueEvent(object :ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if(snapshot.exists())
                                {
                                    FirebaseDatabase.getInstance().reference.child("workers").child(uid).setValue(workerdata(name, contact,password,FirebaseAuth.getInstance().currentUser?.uid.toString()))
                                    Toast.makeText(context,"$name's data updated'",Toast.LENGTH_LONG).show()
                                }
                                else{
                                    Toast.makeText(context,"$uid doesn't exist",Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })

                    }
                    else
                    {
                        Toast.makeText(context ,"Fields Empty", Toast.LENGTH_LONG ).show()
                    }


                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Magenta,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Update Worker's Info")
            }

            Button(
                onClick = {
                    uid = uid.replace("\\s".toRegex(), "")
                    if( uid.isNotBlank() )
                    {
                        FirebaseDatabase.getInstance().reference.child("workers").child(uid).addListenerForSingleValueEvent(object :ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if(snapshot.exists())
                                {
                                    FirebaseDatabase.getInstance().reference.child("workers").child(uid).removeValue()
                                    Toast.makeText(context,"$name deleted",Toast.LENGTH_LONG).show()
                                }
                                else{
                                    Toast.makeText(context,"$uid doesn't exist",Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })

                    }
                    else
                    {
                        Toast.makeText(context ,"Fields Empty", Toast.LENGTH_LONG ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Magenta,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Delete Worker")
            }


        }
    }
}

data class workerdata(var name : String , var contact : String  , var password : String,var companyuid :  String)
