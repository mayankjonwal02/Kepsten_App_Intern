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
    object addworkers : screen("addworkers")
    object addslots : screen("addslots")
    object pendingservices : screen("pendingservices")
    object workerdetails : screen ( "workerdetails")
    object companymain : screen("companymain")

    object usermain : screen("usermain")

    object workermain : screen("workermain")
    object userpastservices : screen("userpastservices")
    object userpendingservices : screen("userpendingservices")

    object  companydetails : screen("companydetails")

    object companypendingservice :screen("companypendingservice")
    object companyworkerlist : screen("companyworkerlist")

    object workerselfinfo : screen("workerselfinfo")

    object workerrequestdata : screen("workerrequestdata")

    object workerpendingrequest : screen("workerpendingrequest")
}