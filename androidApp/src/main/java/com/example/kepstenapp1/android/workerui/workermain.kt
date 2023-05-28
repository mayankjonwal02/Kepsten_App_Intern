package com.example.kepstenapp1.android.workerui

import androidx.compose.runtime.Composable


//package com.example./.android.USER_UI

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kepstenapp1.android.navigation.navgraph
import com.example.kepstenapp1.android.navigation.screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun workermain(workerid : String = "zafaraiyar")  {
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
                        text ="Kepsten",
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
           workerdrawerhead(navController,workerid)
            workerdrawerbody(navController)

        }
        ,
        drawerShape = RoundedCornerShape(30.dp)



    )
    {

        navgraph(navHostController = navController, context = LocalContext.current, start = screen.addslots.route)
    }


}



@Composable
fun workerdrawerhead(navController: NavHostController, workerid: String) {
    var myname by remember {
        mutableStateOf("Default")
    }
    FirebaseDatabase.getInstance().reference.child("workers").child(workerid).child("name").addValueEventListener(object :ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {

            myname = snapshot.value.toString()


        }

        override fun onCancelled(error: DatabaseError) {
            myname = error.message.toString()
        }

    })

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 40.dp)
        ,
        contentAlignment = Alignment.Center)
    {
        Card(modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Green) {

            Box(modifier = Modifier.padding(30.dp),
                contentAlignment = Alignment.Center)
            {

                Text(text = myname , style = TextStyle(fontSize = 30.sp))
            }

        }
    }
}

//@Preview
@Composable
fun workerdrawerbody(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.workerrequestdata.route) }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Requests", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {
                navController.navigate(screen.workerpendingrequest.route)
            }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Default.CallMissed, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Pending Services", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.workerselfinfo.route) }

            .background(Color.Green)
            .padding(10.dp)

            ,
        ) {

            Icon(imageVector = Icons.Filled.Person2, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Worker's Details", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))





    }


}


