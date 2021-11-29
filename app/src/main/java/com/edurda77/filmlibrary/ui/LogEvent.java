package com.edurda77.filmlibrary.ui;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LogEvent extends IntentService {
    private final String TAG = "IntentServiceLogs";

    public LogEvent() {
        super("name");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        toWriteFile(String.valueOf((Log.d(TAG, "onCreate"))));
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("task");
        Log.d(TAG, "onHandleIntent start: " + label);
        toWriteFile(String.valueOf(Log.d(TAG, "onHandleIntent start: " + label)));
        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onHandleIntent end: " + label);
        toWriteFile(String.valueOf(Log.d(TAG, "onHandleIntent end: " + label)));
    }

    protected void toWriteFile (String string) {
        try {
            FileWriter writer = new FileWriter("logWork.txt", true);
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
