package com.device.id.virtual.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.device.id.virtual.ApplicationClass;
import com.device.id.virtual.R;
import com.device.id.virtual.binders.LocalBinder;
import com.device.id.virtual.constants.Constants;
import com.device.id.virtual.interfaces.IdGeneratedResultHandler;
import com.device.id.virtual.models.CreateDeviceIdReq;
import com.device.id.virtual.utils.TinyDB;

import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class IdProviderService extends Service {

    // Binder for the clients
    private final IBinder binder = new LocalBinder(IdProviderService.this);
    private TinyDB tinyDB;

    private IdGeneratedResultHandler idHandler;
    CreateDeviceIdReq devIdReq;

    protected static final String TAG = "IdProviderService";

    private final String ACTION_APPROVE = "approve";
    private final String ACTION_DENY = "deny";

    @Override
    public void onCreate() {
        super.onCreate();
        tinyDB = ApplicationClass.getTinyDBInstance(IdProviderService.this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void updateNotificationAction(int notificationId, String action) {
        if(idHandler==null) {
            Log.e(TAG, "idHandler is null");
        } else {
            byte[] signature = CryptoService.GenerateVirtualId(devIdReq);

            byte[] id = Arrays.copyOf(signature, 28);
            System.out.println(Arrays.toString(id));
            idHandler.run(Base64.getEncoder().encodeToString(id));
        }
    }

    public void getId(String email, IdGeneratedResultHandler handler) {

        idHandler = handler;

        Intent approveIntent = new Intent(this, NotificationRespHandlerService.class);
        approveIntent.setAction(ACTION_APPROVE);
        PendingIntent approvePendingIntent = PendingIntent.getService(IdProviderService.this, 0, approveIntent, PendingIntent.FLAG_MUTABLE);

        Intent denyIntent = new Intent(this, NotificationRespHandlerService.class);
        denyIntent.setAction(ACTION_DENY);
        PendingIntent denyPendingIntent = PendingIntent.getService(IdProviderService.this, 0, denyIntent, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(IdProviderService.this, "id-req-notify").
                setSmallIcon(R.drawable.ic_launcher_foreground).
                setContentTitle("Device ID Request").
                setContentText("An app is requesting access to your device ID").
                addAction(R.drawable.ic_launcher_foreground, "Approve", approvePendingIntent).
                addAction(R.drawable.ic_launcher_foreground, "Deny", denyPendingIntent).
                setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

        devIdReq = new CreateDeviceIdReq(email, "", Constants.ID_DERIVATION_MODE_EMAIL);
    }
}
