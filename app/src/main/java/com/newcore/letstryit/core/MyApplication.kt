package com.newcore.letstryit.core

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import com.newcore.letstryit.R
import com.newcore.letstryit.services.MyAppsNotificationManager


class MyApplication : Application() {

    private val myAppsNotificationManager: MyAppsNotificationManager by lazy {
        MyAppsNotificationManager.getInstance(this)!!
    }

    override fun onCreate() {
        super.onCreate()
        myAppsNotificationManager.registerNotificationChannelChannel(
            getString(R.string.channel_id),
            getString(R.string.channel_name),
            getString(R.string.channel_description),
        )

        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        FirebaseInstallations.getInstance().id.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i(this.javaClass.name, "Task Failed")
                return@OnCompleteListener
            }
            Log.i(this.javaClass.name, "The result: " + task.result)
        })

    }
}