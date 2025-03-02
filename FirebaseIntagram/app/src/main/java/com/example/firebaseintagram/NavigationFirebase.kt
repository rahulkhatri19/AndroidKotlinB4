package com.example.firebaseintagram

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firebaseintagram.NavigationPath.FIREBASE_AUTH_LOGIN
import com.example.firebaseintagram.NavigationPath.FIREBASE_EMAIL_PHONE
import com.example.firebaseintagram.NavigationPath.HOME_SCREEN
import com.example.firebaseintagram.Utility.IS_EMAIL
import com.example.firebaseintagram.Utility.IS_LOGIN

@Composable
fun NavigationFirebase(modifier: Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FIREBASE_AUTH_LOGIN){
        composable(
            route = FIREBASE_AUTH_LOGIN
        ) {
            FirebaseAuthLogin(navController)
        }

        composable(
            route = "$FIREBASE_EMAIL_PHONE?$IS_EMAIL={$IS_EMAIL}&$IS_LOGIN={$IS_LOGIN}",
            arguments = listOf(
                navArgument(
                    name = IS_EMAIL
                ){
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument(
                    name = IS_LOGIN
                ){
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            val isEmail = it.arguments?.getBoolean(IS_EMAIL, false) ?: false
            val isLogin = it.arguments?.getBoolean(IS_LOGIN, false) ?: false
            FirebasePhoneEmail(navController, isEmail, isLogin)
        }

        composable(route = HOME_SCREEN) {
            FirebaseHome(navController)
        }

    }

}

object NavigationPath {
    const val HOME_SCREEN = "firebase_home"
    const val FIREBASE_AUTH_LOGIN = "firebase_auth_login"
    const val FIREBASE_EMAIL_PHONE = "firebase_email_phone"
}