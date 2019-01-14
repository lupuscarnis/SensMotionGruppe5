package com.example.nicolai.sensmotiongruppe5;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class Achieve_Activity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<Achievements> data;
    TextView achiView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_achieve, drawer_layout, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        achiView = rootView.findViewById(R.id.achiText);


        //facebook share button
        ShareButton fbShareButton =  rootView.findViewById(R.id.fb_share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://google.com"))
                .build();
        fbShareButton.setShareContent(content);




data = getArrayList("key");



        mAdapter = new achiAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        achiView.setText(completedCount() + "/" + data.size());



        return rootView;

        }

public static void completed (int n){
/*int n;
n = -1;
for (int i = 0 ; 1 < data.size(); i++) {
      if (name.equals(data.get(i).getName())){
            n = i; }
      else {
            n = -1; }}
*/


    ArrayList<Achievements> achiAL;
    achiAL = getArrayList("key");


if (achiAL.get(n).getCompleted()== false) {
    Achievements completedachi;
    completedachi = achiAL.get(n);
    completedachi.setCompleted(true);
    saveArrayList(achiAL, "key");


    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "Channel_ID123")
            .setSmallIcon(R.drawable.achi)
            .setContentTitle("Achivement gennemført!")
            .setContentText("Du har fuldført en ny achievement")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    NotificationManagerCompat notificationManager2 = NotificationManagerCompat.from(getApplicationContext());
    notificationManager2.notify(1, mBuilder.build());

}

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
        editor.apply();     // This line is IMPORTANT !!!
    }





}