package com.newcore.letstryit.core

import android.app.Application
import com.newcore.letstryit.core.util.serviceHelper.NotificationManagerHelper


class MyApplication : Application() {

    private val notificationManagerHelper: NotificationManagerHelper by lazy {
        NotificationManagerHelper(this)
    }

    override fun onCreate() {
        super.onCreate()
//        notificationManagerHelper.registerNotificationChannelChannel(
//            getString(R.string.channel_id),
//            getString(R.string.channel_name),
//            getString(R.string.channel_description),
//        )
//
//        FirebaseMessaging.getInstance().isAutoInitEnabled = true
//
//        FirebaseInstallations.getInstance().id.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.i(this.javaClass.name, "Task Failed")
//                return@OnCompleteListener
//            }
//            Log.i(this.javaClass.name, "The result: " + task.result)
//        })

    }
}