package com.example.kepstenapp1.android.workerui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

data class requestdata(

    var paid: String = "not alloted",
    var workername: String = "not applicable",
    var customername: String = " sdidc",
    var customercno: String = " sdidc",
    var workercno: String = " sdidc",
    var customeruid : String = "yes",
    var locality: String = "ghaziabad",
    var currentstatus : String = "Uttar Pradesh",
    var address : String = "full Address",
    var servicename: String = " sdidc",
    var serviceid : String = "defaultservice id",
    var requesttype : String = "new",
    var requestid : String = "xxxxx",
    var price : Double = 123.2

)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun requestlist(workeruid : String = "zafaraiyar") {
    var context = LocalContext.current
    var datalist = remember { mutableStateListOf<requestdata>() }
    var filteredItems = remember { mutableStateListOf<requestdata>() }
    var expanded by remember { mutableStateOf(false) }
    
    var showconfirmationdialog by remember {
        mutableStateOf(false)
    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {

        FirebaseDatabase.getInstance().reference.child("workers").child(workeruid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                var workername = snapshot.child("name").value.toString()
                var workercno = snapshot.child("contact").value.toString()
                for (i in snapshot.child("requests").children) {

//                    if(i.child("companyuid").value.toString() == FirebaseAuth.getInstance().currentUser?.uid.toString()) {

                        var data = requestdata()
                        data.workername = workername
                        data.workercno = workercno
                        data.address = i.child("address").value.toString()
                        data.customercno = i.child("customercno").value.toString()
                        data.locality = i.child("locality").value.toString()
                        data.customername = i.child("customername").value.toString()
                        data.paid = i.child("paid").value.toString()
                    data.serviceid = i.child("serviceid").value.toString()
                    data.servicename = i.child("servicename").value.toString()
                    data.currentstatus = i.child("currentstatus").value.toString()
                    data.customeruid = i.child("customeruid").value.toString()
                    data.price= i.child("price").value.toString().toDouble()

                    data.requestid = i.key.toString()



                    datalist.add(data)





//                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message.toString(),Toast.LENGTH_LONG).show()
            }
        })
        var tosearch by remember { mutableStateOf("") }

// Filter the list of items based on the search query
        if (tosearch.isNotBlank()) {
            filteredItems.clear()
            filteredItems.addAll(
                rememberUpdatedState(
                    datalist.filter { it?.locality?.lowercase()?.startsWith(tosearch.lowercase()) == true }
                        .sortedBy { it?.locality?.lowercase() }
                ).value
            )
        } else {
            filteredItems = datalist
        }




        Column(Modifier.fillMaxSize())
        {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), textAlign = TextAlign.Center, text = "Worker's Data", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal)

            Spacer(modifier = Modifier.height(40.dp))


            // Elements below the search bar
            Spacer(modifier = Modifier.height(16.dp))
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
                                    text = "ServiceType: ${data.servicename}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Locality: ${data.locality}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            AnimatedVisibility(visible = expanded) {
                                if (expanded) {
                                    Column {
                                        Text(
                                            text = "Address: ${data.address}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                         Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(15.dp)) {
                                             
                                             Button(onClick = { showconfirmationdialog = !showconfirmationdialog
                                                              }, modifier = Modifier
                                                 .weight(1f)
                                                 , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)) {
                                                 Text(text = "Accept")
                                                 if(showconfirmationdialog)
                                                 {
                                                     PopupScreen(data,workeruid)
                                                 }
                                                 
                                             }
                                             Spacer(modifier = Modifier.width(16.dp))
                                             Button(onClick = {if(data.requesttype == "new")
                                             {
                                                 rejectrequestnew(context,workeruid, data)
                                             }
                                             else{
                                                 rejectrequestold(context,workeruid,data)
                                             }}, modifier = Modifier
                                                 .weight(1f)
                                                 , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)) {
                                                 Text(text = "Reject")

                                             }
                                             
                                         }
                                        
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
@Composable
fun acceptrequest(workeruid:String , data : requestdata,context: Context)
{


    var showit by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Button(onClick = { showit = !showit }) {
            Text(text = "select date")

        }
        if(showit)
        {

        }
    }


}
fun rejectrequestnew(context: Context,workeruid:String , data : requestdata)
{


}

fun rejectrequestold(context: Context,workeruid:String , data : requestdata)
{
    data.currentstatus = "Worker Denied"
    FirebaseDatabase.getInstance().reference.child("customers").child(data.customeruid).child("pendingservices").child(data.serviceid).setValue(data).addOnCompleteListener{
        FirebaseDatabase.getInstance().reference.child("workers").child(workeruid).child("requests").child(data.requestid).removeValue()
        Toast.makeText(context,"Rejected",Toast.LENGTH_LONG).show()
    }



}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PopupScreen(data : requestdata,workeruid : String) {
    val showDialog = remember { mutableStateOf(true) }
    var date by remember {
        mutableStateOf("date")
    }
    var showdatedialog by remember {
        mutableStateOf(false)
    }
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    var formattedDate by remember {
        mutableStateOf("Date")
    }
    var context =   LocalContext.current

    var selectedtime by remember {
        mutableStateOf(LocalTime.now())
    }

    val is24HourFormat =  true

    var showtimedialog by remember {
        mutableStateOf(false)
    }

    var time by remember {
        mutableStateOf("time")
    }

    var ontimesettask = TimePickerDialog.OnTimeSetListener{ _, hrs, min ->
        selectedtime = LocalTime.of(hrs,min)

        time = selectedtime.toString()

        Toast.makeText(context,"${selectedtime.toString()} - ${LocalTime.of(hrs+1,min )}  added",
            Toast.LENGTH_LONG).show()

    }


    if (showDialog.value) {
        AlertDialog(
            shape = RoundedCornerShape(20.dp),
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Card(
                    Modifier.fillMaxWidth(),
                    backgroundColor = Color.Blue,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Select Date & Time",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(30.dp)) // Add spacer with 30.dp height
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column() {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                OutlinedButton(
                                    onClick = { showdatedialog =!showdatedialog },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = Color(android.graphics.Color.parseColor("#add8e6")),
                                        contentColor = Color.Blue
                                    )
                                ) {
                                    Text(text = formattedDate)

                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                OutlinedButton(
                                    onClick = { showtimedialog = ! showtimedialog },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = Color(android.graphics.Color.parseColor("#add8e6")),
                                        contentColor = Color.Blue
                                    )
                                ) {
                                    Text(text = time)
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = { Button(onClick = {

                var status = "$formattedDate at $time"
                data.currentstatus = status

                FirebaseDatabase.getInstance().reference.child("workers").child(workeruid).child("pendingservices").push().setValue(data).addOnCompleteListener {

                    FirebaseDatabase.getInstance().reference.child("workers").child(workeruid).child("requests").child(data.requestid).removeValue()
                    Toast.makeText(context,"Confirmed",Toast.LENGTH_LONG).show()
                    showDialog.value = false
                }


            }, modifier = Modifier

                , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)) {
                Text(text = "Confirm")

            }},
            dismissButton = {
                Button(onClick = { showDialog.value = false }, modifier = Modifier

                    , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)) {
                    Text(text = "Close")

                }
            }
        )
    }
    if (showdatedialog) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                selectedDate.value = calendar.time

                formattedDate = dateFormatter.format(selectedDate.value)
                Toast.makeText(context, "Selected date: $formattedDate", Toast.LENGTH_SHORT).show()
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnDismissListener {
                showdatedialog = false
            }
        }.show()
    }

    if(showtimedialog)
    {

        LaunchedEffect(Unit) {
            val dialog = TimePickerDialog(
                context,
                ontimesettask,
                selectedtime.hour,
                selectedtime.minute,
                is24HourFormat
            )
            dialog.show()
        }
    }
}
