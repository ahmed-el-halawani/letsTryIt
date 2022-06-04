package com.newcore.letstryit.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.newcore.letstryit.R
import com.newcore.letstryit.ui.MainActivity
import com.newcore.letstryit.core.util.serviceHelper.NotificationManagerHelper

const val FOREGROUND_SERVICE_ID = 1;

class ForegroundServiceExample : Service() {


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notification = NotificationManagerHelper(this)
            .notificationBuilder(
                getString(R.string.channel_id),
                "foregroundService",
                "press to navigate to app",
                MainActivity::class.java
            ).build()

        startForeground(FOREGROUND_SERVICE_ID, notification)

        return START_NOT_STICKY;

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}