package com.example.testapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    FloatingActionButton ADD_button,STOP_button;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent intentReceiver,intentNotification;
    LinearLayout linearLayout;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ADD_button = (FloatingActionButton) findViewById(R.id.ADD_button);
        STOP_button = (FloatingActionButton) findViewById(R.id.STOP_button);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" YOUR ALARM");
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intentReceiver = new Intent(this, AlarmReceiver.class);
        intentNotification = new Intent(this,AlarmNotification.class);
        ADD_button.setOnClickListener(view -> {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            intentReceiver.putExtra("Extra", "On");
            startService(intentNotification);
            pendingIntent = PendingIntent.getBroadcast(
                    MainActivity.this, 0, intentReceiver,
                    PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
            );
            int hours = timePicker.getHour();
            int minute = timePicker.getMinute();
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            @SuppressLint("DefaultLocale") String s = String.format("Alarm at %02d : %02d ",hours,minute);
            TextView textView1 = new TextView(this);
            textView1.setText(s);
            textView1.setTextSize(30);
            linearLayout.addView(textView1);
        });
        STOP_button.setOnClickListener(view -> {
            alarmManager.cancel(pendingIntent);
            stopService(intentNotification);
        });
    }

}
