package com.example.kepstenapp1.android.USER_UI

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kepstenapp1.android.navigation.navgraph
import com.example.kepstenapp1.android.navigation.screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun usermain() {
    var navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
        var scaffoldState = rememberScaffoldState()
        Scaffold(
            modifier = Modifier.fillMaxSize()
                    ,
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Kepsten",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    },
                    backgroundColor = Color.Green,
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(
                            onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                        ) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                        }
                    }
                )
            },
            drawerContent = {
                userdrawerhead(navController)
                userdrawerbody(navController)
            }



        )
        {

            navgraph(navHostController = navController, context = LocalContext.current, start = screen.services.route)
        }


    }



@Composable
fun userdrawerhead(navController: NavHostController) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 64.dp)
    ,
    contentAlignment = Alignment.Center)
    {
        Text(text = "Kepsten", fontSize = 60.sp)
    }
}

//@Preview
@Composable
fun userdrawerbody(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {navController.navigate(screen.services.route)}

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.Computer, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Select Service", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {}

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Default.AddTask, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Pending services", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {}

            .background(Color.Green)
            .padding(10.dp)

            ,
        ) {

            Icon(imageVector = Icons.Filled.Checkroom, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Past Services", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {navController.navigate(screen.userinfo.route)}

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.Face, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "User Info", modifier = Modifier.weight(1f))

        }


    }


}