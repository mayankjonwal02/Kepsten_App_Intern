package com.example.kepstenapp1.android.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kepstenapp.android.UI.splashscreen
import com.example.kepstenapp1.android.USER_UI.*
import com.example.kepstenapp1.android.way

@Composable
fun navgraph(navHostController: NavHostController,context: Context,start : String ) {

    NavHost(navController = navHostController, startDestination = start)
    {
        composable(screen.splashscreen.route)
        {
            splashscreen(navHostController,context)
        }

        composable(screen.wayscreen.route)
        {
            way(navHostController,context)
        }

        composable(screen.signinscreen.route+"/{user}/{id}")
        {
            var user = it.arguments?.getString("user")
            var id = it.arguments?.getString("id")?.toInt()
            if (user != null) {
                if (id != null) {
                    signinscreen(user= user,key = id,context,navHostController)
                }
            }
        }

        composable(screen.signupscreen.route+"/{user}")
        {
            var user = it.arguments?.getString("user")

            if (user != null) {
                signupscreen(user = user, navHostController= navHostController, context = context)
            }

        }

        composable(route = screen.services.route)
        {


                mylist(heading = "Select Services",navHostController=navHostController)




        }
        
        composable(route = screen.companies.route+"/{service}")

        {
            var service = it.arguments?.getString("service")
            if (service != null) {
                my2itemlist(service = service, heading = "Select Brand",navHostController=navHostController)
            }
        }

        composable(screen.paymentdue.route+"/{service}/{company}/{cost}")
        {
            var service = it.arguments?.getString("service")
            var company = it.arguments?.getString("company")
            var cost = it.arguments?.getString("cost")?.toDouble()
            if (service != null) {
                if (company != null) {
                    if (cost != null) {
                        payment(
                            service = service,
                            company = company,
                            amount = cost,
                            navHostController = navHostController
                        )
                    }
                }
            }
        }

        composable(screen.ordersuccessful.route)
        {
            ordered(navHostController)
        }

        composable(screen.paymentdone.route)
        {
            PaymentCompleteScreen(navHostController)
        }

        composable(screen.userinfo.route)
        {
            userinfo()
        }



    }

}