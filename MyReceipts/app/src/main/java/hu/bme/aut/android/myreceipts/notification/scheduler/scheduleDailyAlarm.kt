package hu.bme.aut.android.myreceipts.notification.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.startActivity
import hu.bme.aut.android.myreceipts.notification.broadcast_receiver.NotificationAlarmReceiver
import java.util.Calendar
import java.util.Date

fun scheduleDailyAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, NotificationAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0, // Request code
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 18)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)

        if (timeInMillis <= System.currentTimeMillis()) {
            add(Calendar.DAY_OF_YEAR, 1)
        }
    }

    val nextTriggerTime = calendar.timeInMillis

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                nextTriggerTime,
                pendingIntent
            )
            Log.d("Alarm", "Daily alarm scheduled for ${Date(nextTriggerTime)}")
        } else {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
    }
}
