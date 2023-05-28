import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kepstenapp1.android.USER_UI.pendingdata
import com.example.kepstenapp1.android.USER_UI.servicedata
import com.example.kepstenapp1.android.workerui.PopupScreen
import com.example.kepstenapp1.android.workerui.rejectrequestnew
import com.example.kepstenapp1.android.workerui.rejectrequestold
import com.example.kepstenapp1.android.workerui.requestdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun workerpendingservices(workeruid : String = "zafaraiyar" ) {

    var context = LocalContext.current
    var datalist = remember { mutableStateListOf<requestdata>() }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        FirebaseDatabase.getInstance().reference.child("workers").child(workeruid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                var workername = snapshot.child("name").value.toString()
                var workercno = snapshot.child("contact").value.toString()
                for (i in snapshot.child("pendingservices").children) {

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
                    data.requestid = i.key.toString()
                    data.price= i.child("price").value.toString().toDouble()



                    datalist.add(data)





//                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
        Column(Modifier.fillMaxSize())
        {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), textAlign = TextAlign.Center, text = "Pending Services", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal)

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
                                    text = "Status: ${data.currentstatus}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            AnimatedVisibility(visible = expanded) {
                                if (expanded) {
                                    Column() {

                                            Text(
                                                text = "Customer Name: ${data.customername}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Customer Cno.: ${data.customercno}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                                Text(
                                                    text = "Locality: ${data.locality}",
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))

                                            Text(
                                                text = "Address: ${data.address}",
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
                                        if(data.paid == "no")
                                        {
                                            Text(
                                                text = "Take payment of: ${data.price}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))

                                        }
                                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(15.dp)) {

                                            Button(onClick = {
                                            }, modifier = Modifier
                                                .weight(1f)
                                                , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)) {
                                                Text(text = "Accept")


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