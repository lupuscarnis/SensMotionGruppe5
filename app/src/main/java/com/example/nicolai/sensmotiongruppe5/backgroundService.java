/*package com.example.nicolai.sensmotiongruppe5;


import android.app.IntentService;
import android.content.Intent;

import com.example.nicolai.sensmotiongruppe5.BLL.JSONData;

public class backgroundService extends IntentService {
    double walking;
    @Override
    protected void onHandleIntent(Intent workIntent) {
      walking = JSONData.getWalking();

if (walking >= 60){
    Achieve_Activity.completed(1);
}


    }}*/