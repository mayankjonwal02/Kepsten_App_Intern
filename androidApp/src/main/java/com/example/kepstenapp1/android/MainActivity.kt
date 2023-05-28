package com.example.kepstenapp1.android


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kepstenapp1.android.COMPANY_UI.*
import com.example.kepstenapp1.android.USER_UI.*
import com.example.kepstenapp1.android.navigation.navgraph
import com.example.kepstenapp1.android.navigation.screen
import com.example.kepstenapp1.android.workerui.*
import workerpendingservices


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {


            MyApplicationTheme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    var navcontroller = rememberNavController()

//                    navgraph(navHostController = navcontroller, context = LocalContext.current, start = screen.splashscreen.route)
//                        workermain(workerid = "zafaraiyar")
                  workerpendingservices(workeruid = "zafaraiyar")
//                    PopupScreen(onButton1Click = { /*TODO*/ }, onButton2Click = { /*TODO*/ }) {
                        
                    }
                }

            }
        }
    }




@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

fun hello(a:String): String {
    return a
}