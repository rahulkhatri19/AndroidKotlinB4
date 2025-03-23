package com.geeksforgeek.elearningapp

import android.content.Context
import android.content.Intent

object Utility {

    //  rzp_live_XXXXXXXXXXXXXX
    const val RAZORPAY_SECREAT_KEY = ""

    fun paymentMethod(context:Context){
        val intent = Intent(context, PaymentActivity::class.java)
        context.startActivity(intent)
    }
}