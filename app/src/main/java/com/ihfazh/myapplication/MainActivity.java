package com.ihfazh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startService, startIntentService, startBoundService, stopBoundService;

    private boolean mServiceBound = false;
    private MyBindService myBindService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBindService.MyBinder myBinder = (MyBindService.MyBinder) iBinder;
            myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = findViewById(R.id.start_service);
        startIntentService = findViewById(R.id.start_intent_service);
        startBoundService = findViewById(R.id.start_bound_service);
        stopBoundService = findViewById(R.id.stop_bound_service);


        startService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
        startBoundService.setOnClickListener(this);
        stopBoundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                break;

            case R.id.start_intent_service:
                Intent intentService = new Intent(MainActivity.this, MyIntentService.class);
                intentService.putExtra(MyIntentService.EXTRA_DURATION, 5000L);
                startService(intentService);
                break;

            case R.id.start_bound_service:
                Intent boundIntent = new Intent(MainActivity.this, MyBindService.class);
                bindService(boundIntent, serviceConnection, BIND_AUTO_CREATE);
                break;

            case R.id.stop_bound_service:
                unbindService(serviceConnection);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceBound){
            unbindService(serviceConnection);
        }
    }
}