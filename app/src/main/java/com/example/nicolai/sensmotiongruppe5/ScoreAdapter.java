package com.example.nicolai.sensmotiongruppe5;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Tests.Entry;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Entry> {
    private Activity context;
    private ArrayList<Entry> entList;
    //private ArrayList<String> scores;

    public ScoreAdapter(Activity context1, ArrayList<Entry> entList) {
        super(context1, R.layout.score_layout, entList );
        this.context = context1;
        this.entList = entList;
        //this.names= names;
       // this.scores = scores;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listEntry = inflater.inflate(R.layout.score_layout, null, true);

        TextView nameView = (TextView) listEntry.findViewById(R.id.nameView);
        TextView scoreView = (TextView) listEntry.findViewById(R.id.scoreView);
        for (int i = 0; i < entList.size(); i++) {
            for (int j = i + 1; j < entList.size(); j++) {
                if (entList.get(i).getScore().compareTo(entList.get(i).getScore()) > 0) {
                    Entry enTemp = entList.get(i);
                    entList.set(i,entList.get(i) );
                    entList.set(i, enTemp);
                }
            }
        }

        String name = entList.get(position).getName();
        String score =entList.get(position).getScore();
        nameView.setText("hej");
        // nameView.setText(ent.getName());
        scoreView.setText(score);
        Log.i("adapter", name);

        return listEntry;
    }
}