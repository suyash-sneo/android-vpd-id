package com.device.id.virtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.device.id.virtual.services.IdProviderService;

public class MainActivity extends AppCompatActivity {

    private Button mStartBtn, mStopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent startIntent = new Intent(MainActivity.this, IdProviderService.class);
                startService(startIntent);
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopIntent = new Intent(MainActivity.this, IdProviderService.class);
                stopService(stopIntent);
            }
        });
    }
}