package com.example.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        String key = intent.getExtras().getString("Extra");
        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("Extra",key);
        context.startService(myIntent);
    }
}
