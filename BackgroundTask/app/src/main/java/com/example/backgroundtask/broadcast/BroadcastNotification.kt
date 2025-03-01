package com.example.backgroundtask.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.backgroundtask.utility.NotificationHelper.showNotification

class BroadcastNotification: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneMode = intent?.getBooleanExtra("state", false) ?: return
        if (isAirplaneMode){
            context?.let { showNotification("Airplane Mode On", it) }
        } else {
            context?.let { showNotification("Airplane Mode Off", it) }
        }
    }

}