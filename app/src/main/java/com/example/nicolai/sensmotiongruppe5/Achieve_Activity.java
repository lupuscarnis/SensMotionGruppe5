package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Achieve_Activity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Achievements> data;
    TextView achiView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_achieve, drawer_layout, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        achiView = rootView.findViewById(R.id.achiText);



        data = new ArrayList<>();
        data.add(new Achievements("Velkommen!", true, "Du er nu logget ind for første gang, og er klar til at benytte sens motion applikationen", "For at opnå denne achivement, skal du logge ind for første gang"));
        data.add(new Achievements("test", false, "test", "test"));
        data.add(new Achievements("test", false, "test", "test"));
        mAdapter = new achiAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        achiView.setText(completedCount() + "/" + data.size());

        return rootView;

        }

public void completed (int index){



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






}