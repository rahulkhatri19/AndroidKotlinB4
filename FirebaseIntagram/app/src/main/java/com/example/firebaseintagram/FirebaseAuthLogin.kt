package com.example.firebaseintagram

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseintagram.NavigationPath.FIREBASE_EMAIL_PHONE
import com.example.firebaseintagram.NavigationPath.HOME_SCREEN
import com.example.firebaseintagram.Utility.IS_EMAIL
import com.example.firebaseintagram.Utility.IS_LOGIN
import com.example.firebaseintagram.Utility.WEB_CLIENT_KEY
import com.example.firebaseintagram.Utility.toastMessage
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun FirebaseAuthLogin(navController: NavController) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    Box(Modifier.background(Color.White)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (user != null) {
                navController.popBackStack()
                navController.navigate(HOME_SCREEN)
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {
                    if (it.resultCode == RESULT_OK) {
                        toastMessage(context, "Google Sign In Success")
                    } else {
                        toastMessage(context, "Google Sign In Fail, Try Again")
                    }
                }
            )

            Spacer(Modifier.height(56.dp))

            Image(
                painter = painterResource(R.drawable.ic_instagram_logo),
                "",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Card(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                onClick = {
                    callGoogleSignIn(context, launcher)
                }
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_google_logo),
                        contentDescription = "Google",
                        modifier = Modifier.size(28.dp)
                    )

                    Text(
                        text = "Sign In with Google",
                        modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 16.dp, top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    navController.navigate(route = "$FIREBASE_EMAIL_PHONE?$IS_EMAIL=true&$IS_LOGIN=true")
                }
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Email"
                    )

                    Text(
                        text = "Sign In with Email",
                        modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 16.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Cyan
                ),
                onClick = {
                    navController.navigate(route = "$FIREBASE_EMAIL_PHONE?$IS_EMAIL=false&$IS_LOGIN=true")
                }
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = "Phone"
                    )

                    Text(
                        text = "Sign In with Phone",
                        modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                    )
                }
            }

            Text(
                modifier = Modifier.drawBehind {
                    val strokeWidthPx = 1.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = Color.Blue,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(route = "$FIREBASE_EMAIL_PHONE?$IS_EMAIL=true&$IS_LOGIN=false")
                    },
                text = "Don't have an Account? Sign Up"

            )

        }
    }
}

suspend fun signInWithGoogle(context: Context): BeginSignInResult? {

    val beginSignInRequest = Identity.getSignInClient(context).beginSignIn(
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(WEB_CLIENT_KEY)
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    ).await()
    return beginSignInRequest
}

fun callGoogleSignIn(
    context: Context,
    launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
) {
    GlobalScope.launch(Dispatchers.Default) {
        launcher.launch(
            IntentSenderRequest.Builder(
                signInWithGoogle(context)!!.pendingIntent.intentSender
            ).build()
        )
    }

}

















