package com.example.kepstenapp.android.UI

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.kepstenapp1.android.R
import androidx.compose.animation.core.*
import androidx.navigation.NavHostController
import com.example.kepstenapp1.android.navigation.screen
import kotlinx.coroutines.*


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun splashscreen(navHostController: NavHostController, context: Context) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.kepsten_icon),
            contentDescription = null,
            modifier = Modifier.size(500.dp)

        )

        LaunchedEffect(Unit)
        {
            delay(4000)
            navHostController.navigate(screen.wayscreen.route)

        }

    }
}

//
//@Composable
//fun CallContact(phoneNumber: String, context: Context) {
//    Text(
//        text = phoneNumber,
//        modifier = Modifier.clickable {
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:$phoneNumber")
//            if (intent.resolveActivity(context.packageManager) != null) {
//                context.startActivity(intent)
//            }
//        }
//    )
//}



@Composable
fun CallContact(phoneNumber: String) {
    val context = LocalContext.current
    Text(
        text = phoneNumber,
        modifier = Modifier.clickable {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            }
        }
    )
}
