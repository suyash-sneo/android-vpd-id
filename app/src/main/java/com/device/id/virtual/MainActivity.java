package com.device.id.virtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.device.id.virtual.binders.LocalBinder;
import com.device.id.virtual.services.IdProviderService;

public class MainActivity extends AppCompatActivity {

    private IdProviderService service;
    boolean mBound = false;

    private Button mStartBtn, mStopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind to service
        Intent startIntent = new Intent(MainActivity.this, IdProviderService.class);
        bindService(startIntent, connection, Context.BIND_AUTO_CREATE);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        mStartBtn = findViewById(R.id.start_service_button);
        mStopBtn = findViewById(R.id.stop_service_button);
    }

    private void setupListeners() {

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    int num = service.getRandomNumber();
                    Toast.makeText(MainActivity.this, "Number: " + num, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    // Defines callbacks for service binding, passed to bindService()
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            LocalBinder binder = (LocalBinder) iBinder;
            service = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
}