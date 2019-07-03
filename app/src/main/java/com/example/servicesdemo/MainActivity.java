package com.example.servicesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyReceiver receiver;
    Button startMusic,stopMusic,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMusic = findViewById(R.id.button);
        next = findViewById(R.id.button3);
        stopMusic = findViewById(R.id.button2);
        startMusic.setOnClickListener(this);
        stopMusic.setOnClickListener(this);
        next.setOnClickListener(this);

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver,filter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        switch (id){
            case R.id.button:
                startService(intent);
                break;
            case R.id.button2:
                stopService(intent);
                break;
            case R.id.button3:
                Intent intent2 = new Intent();
                intent2.setAction("com.example.broadcast.MY_NOTIFICATION");
                intent2.putExtra("data","Notice me senpai!");
                sendBroadcast(intent2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
