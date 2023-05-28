package com.example.kepstenapp1.android.COMPANY_UI

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun companydetails(context: Context) {

    var name by remember {

        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser?.email.toString())
    }
    var contact by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    )
    {
        Column(Modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "Company Details", fontSize = 40.sp, modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(50.dp))
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                backgroundColor = Color.Green
            ) {

                Box(modifier = Modifier.padding(20.dp), contentAlignment = Alignment.Center)
                {
                    Column() {

                        OutlinedTextField(
                            value = name, onValueChange = {name = it},
                            modifier = Modifier.fillMaxSize(),
                            label = { Text(text = "Company Name") },
                            placeholder = { Text(text = "Enter Company Name") },
                            leadingIcon = { Icon(imageVector = Icons.Filled.Place, contentDescription = "") }
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedTextField(
                            value = email, onValueChange = {email = it},
                            modifier = Modifier.fillMaxSize(),
                            label = { Text(text = "Company Email") },
                            placeholder = { Text(text = "Enter Company Email") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "") }
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedTextField(
                            value = contact, onValueChange = {contact = it},
                            label = { Text(text = "Company Contact No.") },
                            modifier = Modifier.fillMaxSize(),
                            placeholder = { Text(text = "Enter Contact Details") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            leadingIcon = { Icon(imageVector = Icons.Filled.Call, contentDescription = "") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }

            }
            Spacer(modifier = Modifier.height(30.dp))

            Card(
                Modifier
                    .padding(10.dp)
                    .clickable {
                        if(name.isNotBlank() && email.isNotBlank() && contact.isNotBlank())
                        {
                            FirebaseDatabase.getInstance().reference.child("companies").child(
                                FirebaseAuth.getInstance().currentUser?.uid.toString()).setValue(companydata(name,email,contact))

                        }
                        else
                        {
                            Toast.makeText(context,"Fields Empty", Toast.LENGTH_LONG).show()
                        }
                    }
                    .wrapContentSize()


                ,
                border = BorderStroke(2.dp, Color.Green)
                , backgroundColor = Color.Green.copy(alpha = 0.1f)

            )
            {

                Box(modifier = Modifier
                    .padding(15.dp)
                    .background(Color.Transparent), contentAlignment = Alignment.Center)
                {
                    Text(text = "Update", fontWeight = FontWeight.ExtraBold , fontSize = 20.sp)
                }

            }

        }
    }
}

data class companydata(var companyname : String , var companyemail : String , var companycontact : String , )