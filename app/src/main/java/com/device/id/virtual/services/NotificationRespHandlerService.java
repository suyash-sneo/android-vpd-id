package com.device.id.virtual.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.device.id.virtual.ApplicationClass;
import com.device.id.virtual.MainActivity;
import com.device.id.virtual.binders.LocalBinder;
import com.device.id.virtual.utils.TinyDB;

public class NotificationRespHandlerService extends Service {

    private IdProviderService service;
    boolean mBound = false;

    protected final String TAG = "NotificationRespHandlerService";

    String action = "";

    @Override
    public void onCreate() {
        super.onCreate();

        // Bind to service
        Intent startIntent = new Intent(NotificationRespHandlerService.this, IdProviderService.class);
        bindService(startIntent, connection, Context.BIND_AUTO_CREATE);
    }

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

        if(service==null) {
            Log.e(TAG, "Id Provider Service reference is null");
        } else {
            this.action = action;
        }

        if(action == null) {

        } else if(action.equals("approve")) {

        } else {

        }

        // stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            LocalBinder binder = (LocalBinder) iBinder;
            service = binder.getService();
            mBound = true;

            service.updateNotificationAction(0, action);

            NotificationRespHandlerService.this.stopSelf();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
}
