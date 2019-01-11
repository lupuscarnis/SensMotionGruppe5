package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Achieve_Activity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_achieve, drawer_layout, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Achievements> data = new ArrayList<>();
        data.add(new Achievements("navn", true, "123", "321"));
        data.add(new Achievements("navn2", true, "123", "321"));
        data.add(new Achievements("navn", true, "123", "321"));
        data.add(new Achievements("navn2", true, "123", "321"));
        data.add(new Achievements("navn", true, "123", "321"));
        data.add(new Achievements("navn2", true, "123", "321"));
        data.add(new Achievements("navn", true, "123", "321"));
        data.add(new Achievements("navn2", true, "123", "321"));
        mAdapter = new achiAdapter(data);
        mRecyclerView.setAdapter(mAdapter);








        return rootView;


    }
}