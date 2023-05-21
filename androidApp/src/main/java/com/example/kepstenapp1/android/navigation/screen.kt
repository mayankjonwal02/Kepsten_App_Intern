package com.example.kepstenapp1.android.navigation

sealed class screen(val route : String) {

    object splashscreen : screen("splash")
    object wayscreen : screen("way")
    object signinscreen : screen("signin")
    object signupscreen : screen("signup")
    object services : screen("list1")
    object companies : screen("list2")
    object paymentdue : screen ("paymentdue")
    object paymentdone : screen("paymentdone")
    object ordersuccessful :screen("ordersucessful")
    object userinfo :screen("userinfo")

}