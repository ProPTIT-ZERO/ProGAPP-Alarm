package com.example.testapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    boolean isOn;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        String recieveKey = intent.getExtras().getString("Extra");
        isOn= recieveKey.compareTo("On") == 0;
        if(isOn){
            mediaPlayer = MediaPlayer.create(this,R.raw.suzume);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            isOn=false;
        }else{
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_NOT_STICKY;
    }
}
