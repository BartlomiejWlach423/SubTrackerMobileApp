package com.example.subtracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    public static void paymentDayNotify(Context context, String name, float cost, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    context.getString(R.string.notification_channel_id),
                    context.getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.baseline_subscriptions_24)
                .setContentTitle( name + " " + context.getString(R.string.notification_Payment_title))
                .setContentText(message+ " " + name + " " + context.getString(R.string.cost) + " " + cost + context.getString(R.string.currency))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }

}


