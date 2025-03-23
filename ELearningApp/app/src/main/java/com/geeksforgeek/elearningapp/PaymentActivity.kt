package com.geeksforgeek.elearningapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.geeksforgeek.elearningapp.Utility.RAZORPAY_SECREAT_KEY
import com.razorpay.Checkout
import com.razorpay.PayloadHelper
import com.razorpay.PaymentResultListener

class PaymentActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Checkout.preload(applicationContext)
        val checkout = Checkout()
        checkout.setKeyID(RAZORPAY_SECREAT_KEY)
        val payLoadHelper = PayloadHelper("INR", 100, "123456")
        checkout.open(this, payLoadHelper.getJson())
    }

    override fun onPaymentSuccess(razorpayId: String?) {
        println("Payment Success: Id=$razorpayId")
        finish()
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        println("Error: $response")
        finish()
    }
}




