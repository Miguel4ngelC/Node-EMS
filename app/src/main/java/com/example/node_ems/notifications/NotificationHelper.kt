package com.example.node_ems.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private val channelId = "epcr_sync_channel"
    private val notificationId = 1
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Sincronización ePCR",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Muestra el progreso de subida de reportes al servidor"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Esta es la función estrella de tu demo
    fun showProgress(progress: Int) {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_sys_upload) // Icono genérico de subida de Android
            .setContentTitle("Sincronizando ePCR - Folio #4092")
            .setContentText(if (progress == 100) "Sincronización completada" else "Subiendo datos: $progress%")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(progress < 100) // Si es menor a 100, el usuario no puede deslizar la notificación para borrarla
            .setProgress(100, progress, false) // 100 es el máximo, progress es el actual, false es para que sea determinada

        notificationManager.notify(notificationId, builder.build())
    }
}