package com.example.kepstenapp1.android.USER_UI

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.kepstenapp1.android.R
import com.example.kepstenapp1.android.navigation.screen

@Composable
fun signinscreen(
    user: String = "user-type",
    key: Int = 1,
    context: Context,
    navHostController: NavHostController
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = "#90EE90".color)

        )

    {
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
            ,
            verticalArrangement = Arrangement.Top) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = user,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.White,
                modifier = Modifier
                    //.align(Alignment.TopCenter)
                    .padding(30.dp)
                    .alpha(0.7f)
            )
            Spacer(modifier = Modifier.height(30.dp))
            var email = remember {
                mutableStateOf("")
            }

            var password = remember {
                mutableStateOf("")
            }

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier

                    //.align(androidx.compose.ui.Alignment.Center)
                    .wrapContentSize()
                    .padding(10.dp)
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(20.dp))
                    .zIndex(1f)
                //.alpha(0.5f)
                ,
                backgroundColor = Color.White,
                //elevation = 45.dp


            ) {

                Column(
                    modifier = Modifier

                        .padding(20.dp),
                    Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Text(
                        text = "Sign In",
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 40.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        )
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(value = email.value, onValueChange = {
                        email.value = it

                    },
                        label = {
                            Text(text = "Email")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,

                            ),
                        placeholder = {
                            Text(text = "Type your Email")
                        })
                    Spacer(modifier = Modifier.height(20.dp))


                    OutlinedTextField(value = password.value, onValueChange = {
                        password.value = it

                    },
                        label = {
                            Text(text = "Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Type your Password")
                        })
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            border = BorderStroke(1.dp, "#008000".color),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Sign In", color = "#008000".color)

                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "or", color = Color.Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.google_logoq),
                            contentDescription = "",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .shadow(elevation = 20.dp, shape = RoundedCornerShape(20.dp))

                                .clip(RoundedCornerShape(20.dp)),
                        )


                    }
                    if (key == 1) {
                        Spacer(modifier = Modifier.height(20.dp))
                        TextButton(onClick = { navHostController.navigate(screen.signupscreen.route+"/${user}") }) {
                            Text(text = "Not Registered Yet, Sign Up", fontWeight = FontWeight.Bold)
                        }

                    }


                }

            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}

@Composable
fun signupscreen(
    user: String = "user-type",
    navHostController: NavHostController,
    context: Context
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = "#90EE90".color)

    )

    {
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(10.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
        ,
        verticalArrangement = Arrangement.Top) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = user,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.White,
                modifier = Modifier

                    .padding(30.dp)
                    .alpha(0.7f)
            ,
                textAlign = TextAlign.Center

            )
            var email = remember {
                mutableStateOf("")
            }

            var password = remember {
                mutableStateOf("")
            }

            var repassword = remember {
                mutableStateOf("")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier

                    //.align(androidx.compose.ui.Alignment.Center)
                    .wrapContentSize()
                    .padding(10.dp)
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(20.dp))
                    .zIndex(1f)
                //.alpha(0.5f)
                ,
                backgroundColor = Color.White,
                //elevation = 45.dp



            ) {

                Column(
                    modifier = Modifier

                        .padding(20.dp),
                    Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 40.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        )
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(value = email.value, onValueChange = {
                        email.value = it

                    },
                        label = {
                            Text(text = "Email")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,

                            ),
                        placeholder = {
                            Text(text = "Type your Email")
                        })
                    Spacer(modifier = Modifier.height(20.dp))


                    OutlinedTextField(value = password.value, onValueChange = {
                        password.value = it

                    },
                        label = {
                            Text(text = "Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Type your Password")
                        })
                    Spacer(modifier = Modifier.height(20.dp))


                    OutlinedTextField(value = repassword.value, onValueChange = {
                        password.value = it

                    },
                        label = {
                            Text(text = "Confirm Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Re-Type your Password")
                        })
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            border = BorderStroke(1.dp, "#008000".color),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Sign Up", color = "#008000".color)

                        }


                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Already Registered, Sign In", fontWeight = FontWeight.Bold)
                    }


                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))

