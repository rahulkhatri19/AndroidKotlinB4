package com.example.backgroundtask.utility

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.backgroundtask.R
import com.example.backgroundtask.utility.Utility.CHANNEL_ID
import com.example.backgroundtask.utility.Utility.NOTIFICATION_ID
import com.example.backgroundtask.utility.Utility.NOTIFICATION_TITLE
import com.example.backgroundtask.utility.Utility.VERBOSE_NOTIFICATION_CHANNLE_DESCRIPTION
import com.example.backgroundtask.utility.Utility.VERBOSE_NOTIFICATION_CHANNLE_NAME

object NotificationHelper {

    fun showNotification(message:String, context: Context){
        val name = VERBOSE_NOTIFICATION_CHANNLE_NAME
        val description = VERBOSE_NOTIFICATION_CHANNLE_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setVibrate(LongArray(0))
        if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED){
            return
        }
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }
}















