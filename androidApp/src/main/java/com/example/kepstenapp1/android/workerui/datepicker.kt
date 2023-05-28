package com.example.kepstenapp1.android.workerui

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kepstenapp1.android.R
import java.text.SimpleDateFormat
import java.util.*



@Composable
fun showDatePicker() {
    val context = LocalContext.current
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    var showDialog by remember { mutableStateOf(false) }

    var formattedDate by remember {
        mutableStateOf("Date")
    }

    if (showDialog) {
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
                showDialog = false
            }
        }.show()
    }

    Button(
        onClick = {
            showDialog = true
        },
        modifier = Modifier.padding(16.dp)
    ) {

    }
}

