package com.newcore.letstryit.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.newcore.letstryit.R
import com.newcore.letstryit.util.serviceHelper.NotificationManagerHelper
import kotlinx.coroutines.*

class ExampleJobService : JobService() {
    private val TAG = "ExampleJobService"
    private lateinit var job: Job

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.i(TAG, "onStartJob: ")
        doBackground(p0)
        return true;
    }

    private fun doBackground(params: JobParameters?) {
        job = CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..10) {
                delay(3000)
                withContext(Dispatchers.Main) {
                    if (isActive) {
                        NotificationManagerHelper(this@ExampleJobService)
                            .basicNotification(
                                getString(R.string.channel_id), "backgroundThread $i",
                                "hi from background thread",
                                "hi from background thread",
                            )
                    }
                }
            }
        }

        job.invokeOnCompletion {
            jobFinished(params, false)
        }
    }


    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.i(TAG, "onStopJob: ")
        job.cancel()
        return true;
    }

}