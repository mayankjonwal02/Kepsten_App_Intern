package com.example.kepstenapp1.android.functions

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.kepstenapp1.android.navigation.screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


fun signinnavigation(navHostController: NavController,user : String,context: Context)
{


    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
    val database = FirebaseDatabase.getInstance()
    val categoryRef = currentUserUid?.let { it1 ->
        database.reference.child("users").child(
            it1
        ).child("category")
    }

    categoryRef?.addListenerForSingleValueEvent(object :
        ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val categoryValue = dataSnapshot.getValue(String::class.java)

            if (categoryValue == user) {
                Toast.makeText(context," Registered ", Toast.LENGTH_LONG).show()
                when(user){
                    "Organisation" -> navHostController.navigate(screen.companymain.route)
                    "Customer" -> navHostController.navigate(screen.usermain.route)
                    "Worker" -> navHostController.navigate(screen.workermain.route)
                }
            } else {
                Toast.makeText(context," $user ID not found ", Toast.LENGTH_LONG).show()
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context," Logged Out ", Toast.LENGTH_LONG).show()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle any errors or cancellation here
        }
    })
}