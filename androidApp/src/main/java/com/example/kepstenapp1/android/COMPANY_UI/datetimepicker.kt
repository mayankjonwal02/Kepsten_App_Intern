package com.example.kepstenapp1.android.COMPANY_UI

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePickerDemo() {
    // Create a remember mutable state for storing the selected time
    val selectedTime = remember { mutableStateOf(LocalTime.now()) }
    var showdialog by remember {
        mutableStateOf(false)
    }

    // Show a MaterialAlertDialog when the time picker is clicked
    Button(
        onClick = {
            showdialog = !showdialog
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = selectedTime.value.format(DateTimeFormatter.ofPattern("HH:mm")))
    }

    if(showdialog)
    {
        showTimePickerDialog(selectedTime.value) { newTime ->
            selectedTime.value = newTime
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun showTimePickerDialog(initialTime: LocalTime, onTimeSelected: (LocalTime) -> Unit) {
    val is24HourFormat =  true //     MaterialTheme.colors.isLight
    val context = LocalContext.current

    // Create a remember mutable state for storing the selected time
    val selectedTime = remember { mutableStateOf(initialTime) }

    // Create a callback function to handle the time selection
    val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        selectedTime.value = LocalTime.of(hourOfDay , minute)
        onTimeSelected(selectedTime.value)
        FirebaseDatabase.getInstance().reference.child("companies").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("slots").push().setValue("${selectedTime.value.toString()} - ${LocalTime.of(hourOfDay+1,minute)}").addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(context,"${selectedTime.value.toString()} - ${LocalTime.of(hourOfDay+1,minute)}  added",Toast.LENGTH_LONG).show()
            }
        }

    }

    // Launch the TimePickerDialog
    LaunchedEffect(Unit) {
        val dialog = TimePickerDialog(
            context,
            onTimeSetListener,
            initialTime.hour,
            initialTime.minute,
            is24HourFormat
        )
        dialog.show()
    }
}
