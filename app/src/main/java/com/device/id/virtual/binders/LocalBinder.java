package com.device.id.virtual.binders;

import android.os.Binder;

import com.device.id.virtual.services.IdProviderService;

public class LocalBinder extends Binder {

    IdProviderService service;

    public LocalBinder(IdProviderService service) {
        this.service = service;
    }

    public IdProviderService getService() {
        return this.service;
    }
}
