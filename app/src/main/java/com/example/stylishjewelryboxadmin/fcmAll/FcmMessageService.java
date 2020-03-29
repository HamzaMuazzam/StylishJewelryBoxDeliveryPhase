package com.example.stylishjewelryboxadmin.fcmAll;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.OrderByNotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessageService extends FirebaseMessagingService {
    public static final String TAG = "MYTAG";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        Log.d("MYTAG", "onMessageReceived: ");

//        Log.d("MYTAG", "onMessageReceived: Message received from  " + remoteMessage.getFrom());

        System.out.println(remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
//            String title = remoteMessage.getNotification().getTitle();
//            String body = remoteMessage.getNotification().getBody();
//            Intent intent=new Intent(this, OrderByNotificationActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,
//                    intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//
//            Notification notification = new
//                    NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_panel)
//                    .setContentTitle("title")
//                    .setContentText("body")
//                    .setAutoCancel(true)
//                    .setColor(Color.BLACK)
//                    .setContentIntent(pendingIntent)
//                    .build();
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            manager.notify(1002, notification);
        }
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "onMessageReceived: Data received ");
            Intent intent = new Intent(this, OrderByNotificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            String totalorders = remoteMessage.getData().get("key");
            String location = remoteMessage.getData().get("key1");
            String date = remoteMessage.getData().get("key2");


            intent.putExtra("key", date);
            intent.putExtra("key1", location);
            intent.putExtra("key2", totalorders);

            System.out.println(date + location + totalorders);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Notification notification = new
                    NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_icon_app_delivery_boy)
                    .setContentTitle("New Task")
                    .setContentText("You have new task to do. Click to open")
                    .setAutoCancel(true)
                    .setColor(Color.BLACK)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.notify(1002, notification);
            }


            Intent intent2 = new Intent("FCM");
//            intent2.putExtra("myid", oId);
            sendBroadcast(intent2);


        }


        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {

        Log.d(TAG, "onDeletedMessages: Called:");
        super.onDeletedMessages();

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("MYTAG", "onNewToken: " + s);


    }
}
