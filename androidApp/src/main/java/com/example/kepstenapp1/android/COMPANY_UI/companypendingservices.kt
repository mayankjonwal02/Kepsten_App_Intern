package com.example.kepstenapp1.android.COMPANY_UI

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kepstenapp1.android.USER_UI.servicedata
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

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun companypendingservices() {
    var context = LocalContext.current
    var datalist = remember { mutableStateListOf<companypendindservicedata>() }
    var expanded by remember { mutableStateOf(false) }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {

        FirebaseDatabase.getInstance().reference.child("workers").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (i in snapshot.children) {

                    if(i.child("companyuid").value.toString() == FirebaseAuth.getInstance().currentUser?.uid.toString()) {

                        var data : companypendindservicedata = companypendindservicedata()
                        data.customercontact = i.child("pendingservices").child("customercno").value.toString()
                        data.paid = i.child("pendingservices").child("paid").value.toString()
                        data.status = i.child("pendingservices").child("currentstatus").value.toString()
                        data.servicename = i.child("pendingservices").child("servicename").value.toString()
                        data.serviceman = i.child("name").value.toString()
                        data.servicemancontact = i.child("contact").value.toString()

                        datalist.add(data)





                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })




        Column(Modifier.fillMaxSize())
        {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), textAlign = TextAlign.Center, text = "Pending Services", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal)
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(

                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),

                ) {

                items(items = datalist) { data ->
                    Card(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .clickable { expanded = !expanded },
                        backgroundColor = Color.Green,
                        shape = RoundedCornerShape(20.dp)
                    ) {

                            Column(modifier = Modifier

                                .padding(10.dp),
                                
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = "Service Type: ${data.servicename}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = "Worker Name: ${data.serviceman}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                AnimatedVisibility(visible = expanded) {
                                    if (expanded) {
                                        Column {
                                            Text(
                                                text = "Customer Cno.: ${data.customercontact}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(

                                                text = "Worker Cno: ${data.servicemancontact}",
                                                fontSize = 16.sp
                                            ,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(

                                                text = "Status: ${data.status}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(

                                                text = "Paid: ${data.paid}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
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



            }
            }
        }
    }






data class companypendindservicedata(var servicename: String = "",var status:String = "", var serviceman : String = "",var servicemancontact: String="",var customercontact:String="",var paid: String="")

//@Composable
//fun companyservicedata(
//    companyname: String = "companyname",
//    servicename: String = "servicename",
//    status: String = "not alloted",
//    serviceman: String = "not applicable",
//    price: Double = 123.4,
//    paid: String = "yes"
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Card(
//        modifier = Modifier
//            .padding(10.dp)
//            .fillMaxWidth()
//
////            .animateContentSize(
////                animationSpec = tween(
////                    durationMillis = 300,
////                    easing = LinearOutSlowInEasing
////                )
////
////
////            )
//        ,
//        shape = RoundedCornerShape(30.dp)    //Shapes.small
//        ,
//        onClick = {
//            expanded = !expanded
//
//        },
//        backgroundColor = Color.Green
//    ) {
//        Column(Modifier.padding(16.dp)) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    modifier = Modifier.weight(1f),
//                    text = "company name : $companyname",
//                    fontSize = 20.sp
//                )
//                Text(
//                    modifier = Modifier.weight(1f),
//                    text = "Service : $servicename",
//                    fontSize = 20.sp
//                )
//            }
//
//            AnimatedVisibility(visible = expanded) {
//                if (expanded) {
//                    Column()
//                    {
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
////                            modifier = Modifier.weight(1f),
//                            text = "status : $status",
//                            fontSize = 20.sp
//                        )
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
////                            modifier = Modifier.weight(1f),
//                            text = "ServiceMan : $serviceman",
//                            fontSize = 20.sp
//                        )
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
////                            modifier = Modifier.weight(1f),
//                            text = "price : $price",
//                            fontSize = 20.sp
//                        )
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
////                            modifier = Modifier.weight(1f),
//                            text = "paid : $paid",
//                            fontSize = 20.sp
//                        )
//                    }
//                }
//            }
//
//            Icon(
//                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
//                contentDescription = "Expand/Collapse Icon",
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .clickable { expanded = !expanded }
//                    .padding(top = 8.dp)
//                    .size(24.dp)
//            )
//        }
//    }
//}