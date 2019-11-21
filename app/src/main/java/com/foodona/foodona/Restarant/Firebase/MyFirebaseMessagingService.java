package com.foodona.foodona.Restarant.Firebase;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;


import com.foodona.foodona.MainActivity;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.restaurant.OrederconfirmationActivity;
import com.foodona.foodona.Restarant.restaurant.RestaurentActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String Tag;
    private static final String TAG = "FCM Service";
String User_ID;
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("NEW_TOKEN", s).apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
       // User_ID = AppPreferences.getSavedUser(MyFirebaseMessagingService.this).getId();
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        Log.i(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        // to show notifications when app is running in foreground
        Log.i("onMessageReceived", "title: " + remoteMessage.getNotification().getTitle());
        Log.i("onMessageReceived ", "TAG: " + remoteMessage.getNotification().getTag());
        Log.i("onMessageReceived ", "Tiker " + remoteMessage.getNotification().getTicker());
        Intent intent = new Intent(this, OrederconfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("NOTIFICATION_DATA",remoteMessage.getNotification().getTicker());
        startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());

    }



}
