package com.example.nicolai.sensmotiongruppe5.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.R;
import com.example.nicolai.sensmotiongruppe5.Tests.Entry;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Entry> {
    private Activity context;
    private ArrayList<Entry> entList;


    public ScoreAdapter(Activity context1, ArrayList<Entry> entList) {
        super(context1, R.layout.score_layout, entList);
        this.context = context1;
        this.entList = entList;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listEntry = inflater.inflate(R.layout.score_layout, null, true);

        TextView nameView = listEntry.findViewById(R.id.nameView);
        TextView scoreView = listEntry.findViewById(R.id.scoreView);


        String name = entList.get(position).getName();
        String score = entList.get(position).getScore();

        nameView.setText(name);
        scoreView.setText(score);
        Log.i("adapter", name);

        return listEntry;
    }
}