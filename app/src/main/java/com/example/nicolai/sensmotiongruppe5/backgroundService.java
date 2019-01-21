package com.example.nicolai.sensmotiongruppe5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import com.example.nicolai.sensmotiongruppe5.BLL.DAO;
import com.example.nicolai.sensmotiongruppe5.BLL.JSONData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;




public class backgroundService extends Service {
private Timer mTimer;
static DAO userDAO = DAO.getInstance();
double walking;
double resting;
double standing;
double cycling;
double exercise;


ArrayList<JSONData> data;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
super.onCreate();
mTimer = new Timer();
mTimer.schedule(timertask,10000, 10*1000);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    TimerTask timertask = new TimerTask() {

        @Override
        public void run(){

            walking = getSum("walking");
            resting = getSum("resting");
            cycling = getSum("cycling");
            standing = getSum("standing");
            exercise = getSum("exercise");


//Testing purpose
//Achieve_Activity.notifications("Channel_ID123", "Elias har gået " + Double.toString(walking), "Elias har gået " + Double.toString(walking));


                if (walking >= 60) {
                    Achieve_Activity.completed(4); }

                if (walking >= 60*10) {
                    Achieve_Activity.completed(5); }

                if (walking >= 60*1000) {
                    Achieve_Activity.completed(6); }



            if (cycling >= 60) {
                Achieve_Activity.completed(4); }

            if (cycling >= 60*10) {
                Achieve_Activity.completed(5); }

            if (cycling >= 60*1000) {
                Achieve_Activity.completed(6); }



  }};

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent("com.example.nicolai.sensmotiongruppe5.broadcastReceiver");
        sendBroadcast(broadcastIntent);

    }

    public static Double getSum(String activity) {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData("k5W2uX", "6rT39u", 7);

        double data = 0;

        for (int i = 0; i < valuesArray.size(); i++) {

            switch (activity) {
                case "resting":
                    data += valuesArray.get(i).getResting();
                    break;
                case "standing":
                    data += valuesArray.get(i).getStanding();
                    break;
                case "walking":
                    data += valuesArray.get(i).getWalking();
                    break;
                case "cycling":
                    data += valuesArray.get(i).getCycling();
                    break;
                case "exercise":
                    data += valuesArray.get(i).getExercise();
                    break;
                case "other":
                    data += valuesArray.get(i).getOtherd();
                    break;
                case "steps":
                    data += valuesArray.get(i).getSteps();
                    break;
                default:
                    data = 0;


            }
        }

        return data;


}}