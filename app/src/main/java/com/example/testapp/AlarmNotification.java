package com.example.testapp;

import static com.example.testapp.MyAlarm.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class AlarmNotification extends Service {
    @Override
    public void onCreate(){
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startID){
        sendNotification("DON'T BE LAZY");
        return START_NOT_STICKY;
    }
    private void sendNotification(String dataNotification) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this,0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this,CHANNEL_ID)
                .setContentTitle("WAKE UP")
                .setContentText(dataNotification)
                .setSmallIcon(R.drawable.baseline_access_alarm_24)
                .setContentIntent(pendingIntent)
                .build();
            startForeground(1,notification);
        }
    }
    @Override
    public void onDestroy(){ super.onDestroy(); }
}
