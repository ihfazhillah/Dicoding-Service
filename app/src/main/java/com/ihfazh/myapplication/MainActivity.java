package com.ihfazh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startService, startIntentService, startBoundService, stopBoundService;

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
                break;

            case R.id.start_bound_service:
                break;

            case R.id.stop_bound_service:
                break;
        }

    }
}