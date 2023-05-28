import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kepstenapp1.android.R
import com.example.kepstenapp1.android.functions.signinnavigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun oneTapGoogleSignIn(context: Context) {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val token = stringResource(R.string.default_web_client_id)
    val navHostController = rememberNavController()
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            if (result.user != null) {
                user = result.user
            } else {
                user = null
            }
        },
        onAuthError = {
            user = null
        },
        context = context,

        user = "",
        navHostController
    )

    Column {
        if (user == null) {
            Text("Not logged in")
            Button(onClick = {
                val gso =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }) {
                Text("Sign in via Google")
            }
        } else {
            Text("Welcome ${FirebaseAuth.getInstance().currentUser?.uid.toString()}")
            Button(onClick = {
                Firebase.auth.signOut()
                user = null

            }) {
                Text("Sign out")
            }
        }
    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit,
    context: Context,
    user : String,
    navHostController: NavController
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Welcome ${FirebaseAuth.getInstance().currentUser?.email.toString()}",
                        Toast.LENGTH_LONG
                    ).show()

                    FirebaseDatabase.getInstance().reference.child("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .addListenerForSingleValueEvent(object  : ValueEventListener
                        {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if( snapshot.exists())
                                {

                                }
                                else
                                {
                                    FirebaseDatabase.getInstance().reference.child("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("category").setValue(user)

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }

                        })


                    signinnavigation(navHostController,user, context)

                }
            }
        } catch (e: ApiException) {
            onAuthError(e)
            Toast.makeText(
                context,
                e.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
