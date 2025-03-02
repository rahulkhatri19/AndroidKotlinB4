package com.example.firebaseintagram

import android.content.Context
import android.widget.Toast

object Utility {
    const val IS_EMAIL = "is_email"
    const val IS_LOGIN = "is_login"

    fun toastMessage(context: Context, message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    const val WEB_CLIENT_KEY = ""
}

