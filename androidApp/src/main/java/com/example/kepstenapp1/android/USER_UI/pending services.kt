package com.example.kepstenapp1.android.USER_UI

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class pendingdata(var companyname: String = "companyname",
                       var servicename: String = "servicename",
                       var status: String = "not alloted",
                       var serviceman: String = "not applicable",
                       var price: Double = 123.4,
                       var paid: String = "yes")
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun pendingservices() {

    var datalist = remember { mutableStateListOf<pendingdata>() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        FirebaseDatabase.getInstance().reference.child("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("pendingservices").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for(i in snapshot.children)
                {
                    var a = pendingdata(i.child("companyname").value.toString(),i.child("servicename").value.toString(),i.child("currentstatus").value.toString(),i.child("serviceman").value.toString(),i.child("price").value.toString().toDouble(),i.child("paid").value.toString())

                    datalist.add(a)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        Column(Modifier.fillMaxSize())
        {
            Text(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp), textAlign = TextAlign.Center, text = "Pending Services", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal)
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(

                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),

                ) {

                items(items = datalist) { data ->
                    if (data != null) {
                        servicedata(
                            data.companyname,
                            data.servicename,
                            data.status,
                            data.serviceman,
                            data.price.toDouble(),
                            data.paid
                        )
                    }

                }
            }
        }
    }

    }


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun servicedata(
    companyname: String = "companyname",
    servicename: String = "servicename",
    status: String = "not alloted",
    serviceman: String = "not applicable",
    price: Double = 123.4,
    paid: String = "yes"
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

//            .animateContentSize(
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = LinearOutSlowInEasing
//                )
//
//
//            )
            ,
        shape = RoundedCornerShape(30.dp)    //Shapes.small
        ,
        onClick = {
            expanded =! expanded
        },
        backgroundColor = Color.Green
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "company name : $companyname",
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Service : $servicename",
                    fontSize = 20.sp
                )
            }

            AnimatedVisibility(visible = expanded) {
                if(expanded)
                {
                Column()
                    {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
//                            modifier = Modifier.weight(1f),
                            text = "status : $status",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
//                            modifier = Modifier.weight(1f),
                            text = "ServiceMan : $serviceman",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
//                            modifier = Modifier.weight(1f),
                            text = "price : $price",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
//                            modifier = Modifier.weight(1f),
                            text = "paid : $paid",
                            fontSize = 20.sp
                        )
                    }}
            }

            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand/Collapse Icon",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { expanded = !expanded }
                    .padding(top = 8.dp)
                    .size(24.dp)
            )
        }
    }
}
