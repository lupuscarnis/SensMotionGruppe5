package com.example.nicolai.sensmotiongruppe5;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class Achieve_Activity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<Achievements> data;
    private TextView achiView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_achieve, drawer_layout, false);


        mRecyclerView = rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        achiView = rootView.findViewById(R.id.achiText);





data = getArrayList("key");



        mAdapter = new achiAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        achiView.setText(completedCount() + "/" + data.size());



        return rootView;

        }

public static void completed (int n){

    ArrayList<Achievements> achiAL;
    achiAL = getArrayList("key");

    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());



if (!achiAL.get(n).getCompleted()) {
    Achievements completedachi;
    completedachi = achiAL.get(n);
    completedachi.setCompleted(true);
    completedachi.setDate(currentDate);
    saveArrayList(achiAL, "key");

    Achinotifications("Channel_ID123", "Achivement gennemført!",  completedachi.getName() + " gennemført!");

}

}

public static void Achinotifications (String id, String title, String text){


   Intent notiIntent = new Intent(getApplicationContext(), nav_drawer.class);
   notiIntent.putExtra("achi", "achi");
   PendingIntent notiPendingIntent = PendingIntent.getActivity(getApplicationContext(),1,notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);


    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), id)
            .setSmallIcon(R.drawable.achi)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(notiPendingIntent);

    NotificationManagerCompat notificationManager2 = NotificationManagerCompat.from(getApplicationContext());
    notificationManager2.notify(1, mBuilder.build());






}
public static void Rutenotifications (String id, String title, String text){


        Intent notiIntent = new Intent(getApplicationContext(), nav_drawer.class);
        PendingIntent notiPendingIntent = PendingIntent.getActivity(getApplicationContext(),1,notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), id)
                .setSmallIcon(R.drawable.senslogogreybg)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(notiPendingIntent);

        NotificationManagerCompat notificationManager2 = NotificationManagerCompat.from(getApplicationContext());
        notificationManager2.notify(1, mBuilder.build());






    }

public String completedCount(){

        int n = 0;

for (int i = 0; i < data.size(); i++){

   if (data.get(i).getCompleted()){

n = n + 1;

   }
}
String count = Integer.toString(n);
return count;



}



    private static ArrayList<Achievements> getArrayList( String key){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Achievements>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveArrayList(ArrayList<Achievements> list, String key){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }





}