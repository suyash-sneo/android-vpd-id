package com.device.id.virtual.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class NotificationRespHandlerService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("HELLO WORLD");
        String action = intent.getAction();
        System.out.println("Notification handler started with action: " + action);

        if(action == null) {

        } else if(action.equals("approve")) {

        } else {

        }

        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }
}
