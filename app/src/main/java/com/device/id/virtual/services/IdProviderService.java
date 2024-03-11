package com.device.id.virtual.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.device.id.virtual.binders.LocalBinder;

import java.util.Random;

public class IdProviderService extends Service {

    // Binder for the clients
    private final IBinder binder = new LocalBinder(IdProviderService.this);

    private final Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt();
    }
}
