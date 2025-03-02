package com.example.firebaseintagram

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseintagram.NavigationPath.HOME_SCREEN
import com.example.firebaseintagram.Utility.toastMessage
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun FirebasePhoneEmail(navController: NavController, isEmail: Boolean, isLogin: Boolean) {

    var inputEmail by remember { mutableStateOf(TextFieldValue("")) }
    var inputPassword by remember { mutableStateOf(TextFieldValue("")) }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val emailLabel = if (isEmail) "Email" else "Phone Number"
    val passwordLabel = if (isEmail) "Password" else "OTP"
    val buttonLabel = if (isLogin) "LogIn" else "SignUp"

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Spacer(Modifier.height(56.dp))

        Image(
            painter = painterResource(R.drawable.ic_instagram_logo),
            "",
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = inputEmail,
            onValueChange = {
                inputEmail = it
            },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            label = {
                Text(emailLabel)
            },
            placeholder = {
                Text(emailLabel)
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = inputPassword,
            onValueChange = {
                inputPassword = it
            },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            label = {
                Text(passwordLabel)
            },
            placeholder = {
                Text(passwordLabel)
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                navController.popBackStack()
                if (isLogin) {
                    loginAuth(isEmail, auth, context, inputEmail.text, inputPassword.text, navController)
                } else {
                    auth.createUserWithEmailAndPassword(inputEmail.text, inputPassword.text)
                        .addOnCompleteListener(context as Activity) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                toastMessage(context, "Sign up with Email Success")
                                navController.navigate(HOME_SCREEN)
                                val user = auth.currentUser
                            } else {
                                // If sign in fails, display a message to the user.
                                toastMessage(context, "Sign Up Failed : ${task.exception}")
                            }
                        }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(buttonLabel)
        }

    }

}

fun loginAuth(
    isEmail: Boolean,
    auth: FirebaseAuth,
    context: Context,
    email: String,
    password: String,
    navController:NavController
) {
    if (isEmail) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    toastMessage(context, "Sign In with Email success")
                    navController.navigate(HOME_SCREEN)
                    val user = auth.currentUser
                    //    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    toastMessage(context, "Sign In with Email Fail: ${task.exception}")
                }

            }
    } else {
        signInWithPhoneAuth(auth, context, email, password, navController)
    }
}

fun signInWithPhoneAuth(
    auth: FirebaseAuth,
    context: Context,
    phoneNumber: String,
    otp: String,
    navController:NavController
) {

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            toastMessage(context, "Sign In with Phone Verification complete")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            toastMessage(context, "Phone Verification fail: ${e.message}")

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                toastMessage(context, "Phone Verification fail : Invalid request")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                toastMessage(context, "Phone Verification fail : SMS quota exceeded")
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
                toastMessage(context, "Phone Verification fail : reCAPTCHA")
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            val credential = PhoneAuthProvider.getCredential(verificationId, otp)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        toastMessage(context, "Phone SignIn success")
                        navController.navigate(HOME_SCREEN)
                        val user = auth.currentUser
                        //    updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        toastMessage(context, "Phone sign in fail: ${task.exception}")

                        //   updateUI(null)
                    }

                }
        }
    }

    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(phoneNumber) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(context as Activity) // Activity (for callback binding)
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}


