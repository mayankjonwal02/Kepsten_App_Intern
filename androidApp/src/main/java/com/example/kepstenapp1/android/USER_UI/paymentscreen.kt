package com.example.kepstenapp1.android.USER_UI

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kepstenapp1.android.navigation.screen


//@Preview
//@Composable
//fun payment(company:String = "Company name",service:String="Service name",amount:Double=1000.0) {
//
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(color = Color.Green))
//    {
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 30.dp)
//            //.wrapContentHeight()
//            //.align(Alignment.TopCenter)
//
//            ,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        )
//        {
//            Text(
//                text = "Payment",
//                style = TextStyle(
//                    fontSize = 70.sp,
//                    color = Color.White,
//                    fontFamily = FontFamily.Cursive,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.ExtraBold,
//                    textAlign = TextAlign.Left
//                )
//            )
//        }
//    }
//
//}



@Composable
fun payment(
    company: String = "Company name",
    service: String = "Service name",
    amount: Double = 1000.0,
    navHostController: NavHostController
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp,)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Payment",
                style = TextStyle(
                    fontSize = 70.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(60.dp))

            Card(modifier = Modifier.wrapContentSize()

                ,


                backgroundColor = Color.White,
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier

                    .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)) {
//                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Service : " + service, modifier = Modifier.fillMaxWidth(), style = TextStyle(fontFamily = FontFamily.Default, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Normal, fontSize = 20.sp, textAlign = TextAlign.Start))
                    Text(text = "Company : " + company, modifier = Modifier.fillMaxWidth(), style = TextStyle(fontFamily = FontFamily.Default, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Normal, fontSize = 20.sp, textAlign = TextAlign.Start))
                    Text(text = "Price : $amount",modifier = Modifier.fillMaxWidth(),  style = TextStyle(fontFamily = FontFamily.Default, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Normal, fontSize = 20.sp, textAlign = TextAlign.Start))
                    Spacer(modifier = Modifier.height(60.dp))
                    OutlinedButton(onClick = { navHostController.navigate(screen.paymentdone.route) },
                        modifier = Modifier.padding(16.dp)
                            ,
                        border = BorderStroke(1.dp,Color.Green)
                        , colors = ButtonDefaults.buttonColors(

                            backgroundColor = Color.Transparent,
                            contentColor = Color.Green,
//                            disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled),
//                            disabledContentColor = Color.Green.copy(alpha = ContentAlpha.disabled),

                        )

                    ) {
                        Text(text = "Make Payment", color = Color.Green, fontWeight = FontWeight.ExtraBold,)
                    }
//                    Spacer(modifier = Modifier.height(0.dp))
                    OutlinedButton(onClick = { navHostController.navigate(screen.ordersuccessful.route) },
                        modifier = Modifier.padding(3.dp),
                        border = BorderStroke(1.dp,Color.Green)
                        , colors = ButtonDefaults.buttonColors(

                            backgroundColor = Color.Transparent,
                            contentColor = Color.Green,
//                            disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled),
//                            disabledContentColor = Color.Green.copy(alpha = ContentAlpha.disabled),

                        )

                    ) {
                        Text(text = "Door-Step Payment", color = Color.Green, fontWeight = FontWeight.ExtraBold,)
                    }
                }


            }
        }
    }
}