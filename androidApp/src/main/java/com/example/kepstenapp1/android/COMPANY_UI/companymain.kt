package com.example.kepstenapp1.android.COMPANY_UI

import androidx.compose.ui.tooling.preview.Preview

//package com.example./.android.USER_UI

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
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
@Preview
@Composable
fun companymain() {
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
            companydrawerhead(navController)
            companydrawerbody(navController)

        }
        ,
        drawerShape = RoundedCornerShape(30.dp)



    )
    {

        navgraph(navHostController = navController, context = LocalContext.current, start = screen.addslots.route)
    }


}



@Composable
fun companydrawerhead(navController: NavHostController) {

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
                var companyname by remember {
                    mutableStateOf("")
                }
                FirebaseDatabase.getInstance().reference.child("companies").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("companyname").addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        companyname = snapshot.value.toString().toUpperCase()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        companyname = error.message.toString()
                    }

                })
                Text(text = companyname , style = TextStyle(fontSize = 30.sp))
            }
            
        }
    }
}

//@Preview
@Composable
fun companydrawerbody(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.addslots.route) }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Edit Slots", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable {
                navController.navigate(screen.addworkers.route)
            }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Default.AssignmentInd, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Update Workers Data", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.companypendingservice.route) }

            .background(Color.Green)
            .padding(10.dp)

            ,
        ) {

            Icon(imageVector = Icons.Filled.CallMissed, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Pending Services", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.companyworkerlist.route) }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.Face, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Workers Info", modifier = Modifier.weight(1f))

        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
//            .height(10.dp)
            .clickable { navController.navigate(screen.companydetails.route) }

            .background(Color.Green)
            .padding(10.dp)
            ,
        ) {

            Icon(imageVector = Icons.Filled.Place, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Company Details", modifier = Modifier.weight(1f))

        }


    }


}


