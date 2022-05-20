package com.newcore.letstryit.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.newcore.letstryit.R
import com.newcore.letstryit.ui.MainActivity
import com.newcore.letstryit.util.serviceHelper.MyAppsNotificationManager

data class MyMessage(val title: String, val message: String)

fun RemoteMessage.toMyMessage(): MyMessage = MyMessage(
    title = data["title"] ?: "no title",
    message = data["message"] ?: "no message"
)

class MyFirebaseCloudMassageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(this.javaClass.name, "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val myMessage = message.toMyMessage()


        message.notification?.let {
            MyAppsNotificationManager.getInstance(applicationContext)!!.triggerNotification(
                MainActivity::class.java,
                applicationContext.getString(R.string.channel_id),
                it.title,
                it.body,
                it.body,
                1,
            )
        }



        Log.i(this.javaClass.name, "onNewToken: $myMessage")
    }
}