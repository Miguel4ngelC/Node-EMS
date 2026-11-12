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

    fun showProgress(progress: Int) {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(if (progress == 100) android.R.drawable.stat_sys_download_done else android.R.drawable.stat_sys_upload)
            .setContentTitle(if (progress == 100) "¡Lote ePCR Sincronizado!" else "Sincronizando ePCR - Lote 3 folios")
            .setContentText(if (progress == 100) "Sincronización completada con éxito." else "Subiendo datos: $progress%")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(progress < 100)

        if (progress < 100) {
            builder.setProgress(100, progress, false) // Muestra progreso
        } else {
            builder.setProgress(0, 0, false) // Oculta la barra al terminar
        }

        notificationManager.notify(notificationId, builder.build())
    }
}