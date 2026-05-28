package hu.bme.aut.android.myreceipts.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import hu.bme.aut.android.myreceipts.R

class NotificationHelper(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val NOTIFICATION_ID = 101
        const val NOTIFICATION_CHANNEL_NAME = "ALARM_NOTIFICATION"
        const val NOTIFICATION_CHANNEL_ID = "ALARM_NOTIFICATION_ID"
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val alarmChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(alarmChannel)
        }
    }

    fun showNotification(recipe: String, pendingIntent: PendingIntent) {
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(context.getString(R.string.notif_title))
                .setContentText(context.getString(R.string.notif_text) + " $recipe")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}