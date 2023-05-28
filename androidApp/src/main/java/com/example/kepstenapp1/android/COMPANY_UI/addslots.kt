package com.example.kepstenapp1.android.COMPANY_UI


import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.play.integrity.internal.c
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
//@Preview
@Composable
fun addslots(context: Context) {

    var showtimepicker by remember {
        mutableStateOf(false)
    }

    var slotlist =  remember {
        mutableStateListOf<String?>("No Slots to Display")
    }

    FirebaseDatabase.getInstance().reference.child("companies").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("slots").addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            if(snapshot.exists())
            {
                slotlist.clear()

                for (i in snapshot.children)
                {
                    slotlist.add(i.value.toString())
                }
            }
            else
            {
                slotlist.clear()
                slotlist.add("No Slots to Display")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO()
        }

    })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState())

    )

    {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                text = "Slots",
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { showtimepicker = !showtimepicker }
                    .shadow(30.dp, RoundedCornerShape(20.dp), spotColor = Color.Blue),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color.Green,
            ) {
                Box(modifier = Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
                    Text(text = "Add Slots", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier

                    .fillMaxWidth()
            ) {
                slotlist.sortedBy { slot ->
                    val parts = slot?.split(" - ")
                    val startTime = parts?.get(0)

                    // Convert the start time to a comparable value (e.g., minutes since midnight)
                    val startTimeParts = startTime?.split(":")
                    val startHour = startTimeParts?.get(0)?.toInt() ?: 0
                    val startMinute = startTimeParts?.get(1)?.toInt() ?: 0
                    val startTimeValue = startHour * 60 + startMinute

                    startTimeValue
                }.forEach { data ->
                    Spacer(modifier = Modifier.height(15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        shape = RoundedCornerShape(25.dp),
                        backgroundColor = Color.Green
                    ) {
                        if (data != null) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically)
                            {
                                Text(
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),
                                    text = data,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                if( !data.contains("No"))
                                {
                                    Icon(imageVector = Icons.Default.Delete,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clickable {
                                                FirebaseDatabase.getInstance().reference
                                                    .child("companies")
                                                    .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                                                    .child("slots")
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            for (i in snapshot.children) {
                                                                if (i.value.toString() == data) {
                                                                    FirebaseDatabase.getInstance().reference
                                                                        .child("companies")
                                                                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                                                                        .child("slots")
                                                                        .child(i.key.toString())
                                                                        .removeValue()

                                                                    Toast
                                                                        .makeText(
                                                                            context,
                                                                            "$data Deleted",
                                                                            Toast.LENGTH_LONG
                                                                        )
                                                                        .show()

                                                                }
                                                            }
                                                        }

                                                        override fun onCancelled(error: DatabaseError) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    })
                                            })
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showtimepicker) {
        picktime(context = context)
    }
}

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun picktime(context: Context) {

    var selectedtime by remember {
        mutableStateOf(LocalTime.now())
    }

    val is24HourFormat =  true
//    var context = LocalContext.current

    var ontimesettask = TimePickerDialog.OnTimeSetListener{_,hrs,min ->
     selectedtime = LocalTime.of(hrs,min)
        FirebaseDatabase.getInstance().reference.child("companies").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("slots").push().setValue("${selectedtime.toString()} - ${LocalTime.of(hrs+1,min)}").addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(context,"${selectedtime.toString()} - ${LocalTime.of(hrs+1,min )}  added",
                    Toast.LENGTH_LONG).show()
            }
        }}
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


