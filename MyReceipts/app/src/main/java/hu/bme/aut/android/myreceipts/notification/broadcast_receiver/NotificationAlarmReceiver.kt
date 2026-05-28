package hu.bme.aut.android.myreceipts.notification.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import hu.bme.aut.android.myreceipts.notification.worker.NotificationWorker

class NotificationAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>().build()
        context?.let {
            WorkManager.getInstance(it).enqueue(workRequest)
        }
    }
}