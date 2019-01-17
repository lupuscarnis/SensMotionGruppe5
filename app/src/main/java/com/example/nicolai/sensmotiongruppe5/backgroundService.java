package com.example.nicolai.sensmotiongruppe5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Timer;
import java.util.TimerTask;


public class backgroundService extends Service {
private Timer mTimer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
super.onCreate();
mTimer = new Timer();
mTimer.schedule(timertask,2000, 2*1000);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    TimerTask timertask = new TimerTask() {

        @Override
        public void run(){

//Achieve_Activity.completed(1);



        }};

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent("com.example.nicolai.sensmotiongruppe5.broadcastReceiver");
        sendBroadcast(broadcastIntent);

    }



}