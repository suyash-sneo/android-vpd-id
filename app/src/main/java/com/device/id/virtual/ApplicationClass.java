package com.device.id.virtual;

import android.app.Application;
import android.content.Context;

import com.device.id.virtual.utils.TinyDB;

public class ApplicationClass extends Application {

    static TinyDB tinyDB;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static TinyDB getTinyDBInstance(Context context) {
        if(tinyDB == null) {
            tinyDB = new TinyDB(context);
        }

        return tinyDB;
    }
}
