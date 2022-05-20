package com.newcore.letstryit.util.serviceHelper

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.location.LocationManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.location.LocationManagerCompat
import com.newcore.letstryit.R


internal class MyAppsNotificationManager private constructor(val context: Context) {
    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)


    fun registerNotificationChannelChannel(
        channelId: String?,
        channelName: String?,
        channelDescription: String?,
    ) {
        val notificationChannel = NotificationChannel(channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.description = channelDescription

        val z = context.getSystemService(LocationManager::class.java)  as LocationManager

        notificationManagerCompat.createNotificationChannel(notificationChannel)
    }

    fun triggerNotification(
        targetNotificationActivity: Class<*>,
        channelId: String,
        title: String?,
        text: String?,
        bigText: String?,
        notificationId: Int,
    ) {
        val intent = Intent(context, targetNotificationActivity)
        intent.putExtra("count", title)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                R.drawable.ic_notification))
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setAutoCancel(true)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun cancelNotification(notificationId: Int) {
        notificationManagerCompat.cancel(notificationId)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: MyAppsNotificationManager? = null
        fun getInstance(context: Context): MyAppsNotificationManager? {
            if (instance == null) {
                instance = MyAppsNotificationManager(context)
            }
            return instance
        }
    }

}