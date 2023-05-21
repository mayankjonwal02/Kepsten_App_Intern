package com.example.kepstenapp1.android.USER_UI

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kepstenapp1.android.R
import com.example.kepstenapp1.android.navigation.screen
import kotlinx.coroutines.delay

//@Preview
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun PaymentCompleteScreen() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Image(
//            painter = painterResource(R.drawable.ic_check_circle),
//            contentDescription = "Check Mark",
//            modifier = Modifier
//                .size(96.dp)
//                .padding(bottom = 16.dp),
//            colorFilter = ColorFilter.tint(Color.Green)
//        )
//        Text(
//            text = "Payment Complete",
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//        Text(
//            text = "Thank you for your purchase!",
//            style = MaterialTheme.typography.body1,
//            modifier = Modifier.padding(bottom = 24.dp)
//        )
//        Box(
//            modifier = Modifier
//                .size(48.dp)
//                .background(MaterialTheme.colors.primary, CircleShape)
//        ) {
//            AnimatedVisibility(
//                visible = true,
//                initiallyVisible = false,
//                enter = fadeIn(),
//                exit = fadeOut(),
//                modifier = Modifier.align(Alignment.Center)
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.ic_check_circle),
//                    contentDescription = "Check",
//                    tint = Color.White
//                )
//            }
//        }
//    }
//}



//@Preview
@Composable
fun ordered(navHostController: NavHostController) {
    var checkmarkVisible by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = Color.Green)
    val alpha by animateFloatAsState(targetValue = 1f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_check_circle),
            contentDescription = "Check Mark",
            modifier = Modifier
                .size(96.dp)
                .padding(bottom = 16.dp)
            ,
            colorFilter = ColorFilter.tint(color),
            contentScale = ContentScale.Fit,
            alpha = alpha
        )
        Text(
            text = "Order Successful",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "We will meet soon!",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LaunchedEffect(Unit)
        {
            delay(3000)
            navHostController.navigate(screen.services.route)
        }


    }
}
