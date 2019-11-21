package com.foodona.foodona.Restarant.services;

import android.util.Log;

import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.modal.Login_Model;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMesasagingService extends FirebaseMessagingService {
    Login_Model login_model;
    private static final String TAG = FirebaseMesasagingService.class.getSimpleName();
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("DeviceToken= ", s+"  "+refreshedToken);
        AppPreferences.SaveUserdetail(getApplicationContext(), login_model);

        // Config.setDeviceToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            if (remoteMessage == null)
                return;
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            //handleNotification(remoteMessage.getNotification().getBody());
        }
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getData().size() + "");

            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("message");
            String type = remoteMessage.getData().get("type");
            Log.d("title",title);
          //  NotificationUtils.creatMessageNotification(this, title, message, type);
        }

    }
}
