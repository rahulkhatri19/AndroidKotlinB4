package com.example.backgroundtask.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

val ServiceClassName = ServiceClass::class.java.simpleName
class ServiceClass: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(ServiceClassName, ": Our service is created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(ServiceClassName, ": Our service is on start before")
        Thread.sleep(5000)
        Log.e(ServiceClassName, ": Our service is on start after")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(ServiceClassName, ": Our service is Destroyed")
    }

}