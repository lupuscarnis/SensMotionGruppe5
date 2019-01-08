package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Setting_Activity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_setting, drawer_layout, false);

        createNotification( "test", "test");



        return rootView;


    }

    public void createNotification (String title, String text) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "Channel_ID123")
                .setSmallIcon(R.drawable.sens_logo)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager2 = NotificationManagerCompat.from(getActivity());
       notificationManager2.notify(1, mBuilder.build());
    }

}
