package com.example.kepstenapp1.android.COMPANY_UI

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
fun addworker() {
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
                value = "",
                onValueChange = { },
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
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),

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
                value = "",
                onValueChange = { },
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
                value = "",
                onValueChange = { },
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
                onClick = {},
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
                onClick = {},
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
                onClick = {},
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
