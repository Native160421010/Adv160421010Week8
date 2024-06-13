package com.wildfire.todoapp_160421010.model

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.wildfire.todoapp_160421010.util.NotificationHelper

class TodoWorker(context: Context, params: WorkerParameters): Worker(context,params) {

    override fun doWork(): Result {
        NotificationHelper(applicationContext).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString())
        return Result.success()
    }
}