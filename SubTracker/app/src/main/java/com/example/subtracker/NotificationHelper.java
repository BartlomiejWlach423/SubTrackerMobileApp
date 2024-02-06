package com.example.subtracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Random;


public class NotificationHelper {

    public static void paymentDayNotify(Context context, String name, float cost, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    context.getString(R.string.notification_channel_name),
                    context.getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_name))
                .setSmallIcon(R.drawable.baseline_subscriptions_24)
                .setContentTitle( name + " " + context.getString(R.string.notification_Payment_title))
                .setContentText(message+ " " + name + " " + context.getString(R.string.cost) + " " + cost + context.getString(R.string.currency))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Random random = new Random();
        int notificationId = random.nextInt(1000);

        Notification notification = builder.build();
        notificationManager.notify(notificationId, notification);
    }

}


