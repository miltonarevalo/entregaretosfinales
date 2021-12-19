package com.example.retodos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {

    private ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        progressBar2 = (ProgressBar) findViewById(R.id.progressBar);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar2.incrementProgressBy(20);
            }
        };
        for(int i=0; i<5; i++){
            new Handler().postDelayed(runnable,(i+1)*1000);
        }

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);


    }
}