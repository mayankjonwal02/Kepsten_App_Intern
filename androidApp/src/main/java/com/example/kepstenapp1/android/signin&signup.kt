package com.example.kepstenapp1.android.USER_UI

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.kepstenapp1.android.functions.signinnavigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rememberFirebaseAuthLauncher


@Composable
fun signinscreen(
    user: String = "user-type",
    key: Int = 1,
    context: Context,
    navHostController: NavHostController
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var googlesign by remember {
        mutableStateOf(false)
    }

    var useri by remember { mutableStateOf(Firebase.auth.currentUser) }
    var launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
        useri = result.user
    }, onAuthError = {
        useri = null
    }, context, user , navHostController )
    val token = stringResource(R.string.default_web_client_id)



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
            var email by remember {
                mutableStateOf("")
            }

            var password by remember {
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
                    OutlinedTextField(value = email, onValueChange = {
                        email = it

                    },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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


                    OutlinedTextField(value = password, onValueChange = {
                        password = it

                    },
                        label = {
                            Text(text = "Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Type your Password")


                        }
                    ,
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisibility = !passwordVisibility },
                                content = {
                                    Icon(
                                        imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                                    )
                                }
                            )
                        }

                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (email != "" && password != "")
                                { CoroutineScope(Dispatchers.IO).launch {
                                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
                                        it -> if(it.isSuccessful)
                                    {
                                        signinnavigation(navHostController,user, context)
                                    }
                                    else
                                    {
                                        Toast.makeText(context," Error",Toast.LENGTH_LONG).show()
                                    }
                                    }
                                }
                                }
                                else {
                                    Toast.makeText(context, "Fields Empty", Toast.LENGTH_LONG)
                                        .show()
                                }
                             },
                            border = BorderStroke(1.dp, "#008000".color),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Sign In", color = "#008000".color)

                        }
                        if(key == 1)
                        {
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
                                    .clickable {


                                        val gso =
                                            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                                .requestIdToken(token)
                                                .requestEmail()
                                                .build()
                                        val googleSignInClient =
                                            GoogleSignIn.getClient(context, gso)
                                        launcher.launch(googleSignInClient.signInIntent)
                                    }
                                    .clip(RoundedCornerShape(20.dp)),
                            )

                        }

//                        Image(painter = painterResource(com.example.kepstenapp1.android.R.drawable.google_logoq), contentDescription = "")


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
    if(googlesign)
    {


////        val context = LocalContext.current

    }

}

@SuppressLint("SuspiciousIndentation")
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
            var email by remember {
                mutableStateOf("")
            }

            var password by remember {
                mutableStateOf("")
            }

            var repassword by remember {
                mutableStateOf("")
            }
            var passwordVisibility by remember {
                mutableStateOf(false)
            }

            var passwordVisibility1 by remember {
                mutableStateOf(false)
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
                    OutlinedTextField(value = email, onValueChange = {
                        email = it

                    },
                        label = {
                            Text(text = "Email")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,

                            ),
                        placeholder = {
                            Text(text = "Type your Email")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),)
                    Spacer(modifier = Modifier.height(20.dp))


                    OutlinedTextField(value = password, onValueChange = {
                        password = it

                    },
                        label = {
                            Text(text = "Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Type your Password")
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisibility = !passwordVisibility },
                                content = {
                                    Icon(
                                        imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                                    )
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    var created by remember {
                        mutableStateOf(false)
                    }
                    OutlinedTextField(value = repassword, onValueChange = {
                        repassword = it

                    },
                        label = {
                            Text(text = "Confirm Password")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = "#50C878".color,
                        ),
                        placeholder = {
                            Text(text = "Re-Type your Password")
                        },
                        visualTransformation = if (passwordVisibility1) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisibility1 = !passwordVisibility1 },
                                content = {
                                    Icon(
                                        imageVector = if (passwordVisibility1) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = if (passwordVisibility1) "Hide password" else "Show password"
                                    )
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    var data1 : String = email

                    Row(
                        Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = {


                                if (email.isNotBlank() && password.isNotBlank() && repassword.isNotBlank()) {
                                    if (password == repassword) {
                                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(context,"Registered",Toast.LENGTH_LONG).show()

                                                    created = true
                                                } else {
                                                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                    } else {
                                        Toast.makeText(context, "Password Mismatch", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Fields Empty", Toast.LENGTH_LONG).show()
                                }
                            },
                            border = BorderStroke(1.dp, "#008000".color),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Sign Up", color = "#008000".color)


                        }



                    }
                    if (created)
                    {
                        var ref = FirebaseDatabase.getInstance().reference.child("users")
                            ref.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("category").setValue(user)

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

