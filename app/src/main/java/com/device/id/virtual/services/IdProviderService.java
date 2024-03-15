package com.device.id.virtual.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.device.id.virtual.R;
import com.device.id.virtual.binders.LocalBinder;
import com.device.id.virtual.constants.Constants;
import com.device.id.virtual.models.CreateDeviceIdReq;

import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class IdProviderService extends Service {

    // Binder for the clients
    private final IBinder binder = new LocalBinder(IdProviderService.this);

    private final Random mGenerator = new Random();

    private final String ACTION_APPROVE = "approve";
    private final String ACTION_DENY = "deny";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt();
    }

    public String getId(String email) {

        Intent approveIntent = new Intent(this, IdProviderService.class);
        approveIntent.setAction(ACTION_APPROVE);
        PendingIntent approvePendingIntent = PendingIntent.getService(IdProviderService.this, 0, approveIntent, PendingIntent.FLAG_MUTABLE);

        Intent denyIntent = new Intent(this, IdProviderService.class);
        approveIntent.setAction(ACTION_DENY);
        PendingIntent denyPendingIntent = PendingIntent.getService(IdProviderService.this, 0, denyIntent, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(IdProviderService.this, "id-req-notify").
                setSmallIcon(R.drawable.ic_launcher_foreground).
                setContentTitle("Device ID Request").
                setContentText("An app is requesting access to your device ID").
                addAction(R.drawable.ic_launcher_foreground, "Approve", approvePendingIntent).
                addAction(R.drawable.ic_launcher_foreground, "Deny", denyPendingIntent).
                setPriority(NotificationCompat.PRIORITY_HIGH);


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

        CreateDeviceIdReq req = new CreateDeviceIdReq(email, "", Constants.ID_DERIVATION_MODE_EMAIL);
        byte[] signature = CryptoService.GenerateVirtualId(req);

        byte[] id = Arrays.copyOf(signature, 28);
        System.out.println(Arrays.toString(id));
        return Base64.getEncoder().encodeToString(id);
    }
}
