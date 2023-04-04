package com.example.kepstenapp1.android.USER_UI

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun userinfo() {

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
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "USER INFO",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(100.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .shadow(8.dp)

            ) {
                OutlinedTextField(
                    value = "",
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
                    value = "",
                    onValueChange = { },
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
                    value = "",
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
                    modifier = Modifier.fillMaxWidth()
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
                    value = "",
                    onValueChange = { },
                    label = { Text("Locality") },
                    placeholder = { Text("Enter your Locality") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
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
                    value = "",
                    onValueChange = { },
                    label = { Text("State") },
                    placeholder = { Text("Enter your State") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
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
                    value = "",
                    onValueChange = { },
                    label = { Text("Full Address") },
                    placeholder = { Text("Enter your Address") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
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




    }
}
