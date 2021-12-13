package com.edurda77.filmlibrary.data

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.edurda77.filmlibrary.domain.AlarmUpcomingMovieCase
import com.edurda77.filmlibrary.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmUpcomingMovieCaseImpl : AlarmUpcomingMovieCase {
    @SuppressLint("SimpleDateFormat")
    override fun setAlarm(upcomingMovie: ResultSearchMovie, context: Context) {
        val notificationManager = NotificationManagerCompat.from(context)

        NotificationManagerCompat.from(context)

        createChannelsOnStart(notificationManager)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dateOfMovie = sdf.parse(upcomingMovie.releaseDate)
        val currentDate = sdf.format(Date())
        if (dateOfMovie != null) {
            if (dateOfMovie.equals(currentDate)) {
                notificationManager.notify(1, createNotification(upcomingMovie, context))
            }
        }
    }

    private fun createChannelsOnStart(notificationManager: NotificationManagerCompat) {
        val channel = NotificationChannelCompat.Builder(
            "1",
            NotificationManagerCompat.IMPORTANCE_HIGH
        )
            .setName("Фильм! Фильм! Фильм!")
            .build()
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createNotification(
        upcomingMovie: ResultSearchMovie,
        context: Context
    ): Notification {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        return NotificationCompat.Builder(context, "1")
            .setContentTitle("Сегодня премьера")
            .setContentText(upcomingMovie.title)
            .setContentIntent(pendingIntent)
            .build()
    }
}