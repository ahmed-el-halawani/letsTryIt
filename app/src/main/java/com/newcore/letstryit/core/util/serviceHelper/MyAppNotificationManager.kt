package com.newcore.letstryit.core.util.serviceHelper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.location.LocationManager
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.newcore.letstryit.R
import java.util.*


internal class NotificationManagerHelper constructor(val context: Context) {
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

        val z = context.getSystemService(LocationManager::class.java) as LocationManager

        notificationManagerCompat.createNotificationChannel(notificationChannel)
    }

    @IntDef(NotificationCompat.PRIORITY_DEFAULT,
        NotificationCompat.PRIORITY_HIGH,
        NotificationCompat.PRIORITY_LOW)
    annotation class NotificationPriority

    fun notificationBuilder(
        channelId: String,
        title: String? = null,
        text: String? = null,
        targetNotificationActivity: Class<*>? = null,
        bigText: String? = null,
        autoCancel: Boolean = false,
        @NotificationPriority priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        @DrawableRes smallIcon: Int = R.drawable.ic_notification,
        @DrawableRes largeIcon: Int = R.drawable.ic_notification,
    ): NotificationCompat.Builder {
        var pendingIntent: PendingIntent? = null
        if (targetNotificationActivity != null) {
            val intent = Intent(context, targetNotificationActivity)
            intent.putExtra("count", title)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(smallIcon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, largeIcon))
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setAutoCancel(autoCancel)


        if (bigText != null)
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(bigText))

        return builder;

    }

    fun basicNotification(
        channelId: String,
        title: String?,
        text: String?,
        bigText: String?,
    ) {
        val notification: Notification = notificationBuilder(
            channelId,
            title,
            text,
            bigText = bigText,
        ).build()
        notificationManagerCompat.notify(Random().nextInt(), notification)
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

}