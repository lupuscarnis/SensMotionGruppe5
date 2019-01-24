package com.example.nicolai.sensmotiongruppe5;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Tests.Entry;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Score_Activity extends Fragment {


    Highscore_controller hsc;
    Button b12;
    TextView tv10, titleView;
    private View superView;
    ArrayList<Entry> entryAList;
    ArrayList<String> nameList, scorList;
    String text, text2;
    ListView scoreList;
    ScoreAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {
        Log.i("opens", "onCreate starts");
        if (superView == null) {
            superView = inflater.inflate(R.layout.activity_highscore, drawer_layout, false);
        }
        entryAList = new ArrayList<>();
        nameList = new ArrayList<String>();
        scorList = new ArrayList<>();
        scoreList = superView.findViewById(R.id.ScoreListx);


        hsc = new Highscore_controller();
        hsc.createDatabaseReference();
        b12 = superView.findViewById(R.id.button2);
        tv10 = superView.findViewById(R.id.textView10);
        b12.setVisibility(View.GONE);
        tv10.setVisibility(View.GONE);
        titleView = (TextView) superView.findViewById(R.id.textView11);

        String message = hsc.getKey(1);
        tv10.setText(message);
        Log.i("message", message);


        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entry ent = new Entry("Maj", "2", "11");
                hsc.addScore(ent);
                Log.i("added to database", ent.toString());
                adapter = new ScoreAdapter(getActivity(), entryAList);
                scoreList.setAdapter(adapter);
            }
        });


        hsc.dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.i("data change detected", dataSnapshot.toString());
                entryAList.clear();
                // Entry[] entArray = new Entry[(int) dataSnapshot.getChildrenCount()];
                // for(int i = 0 ; dataSnapshot.getChildrenCount()>i; i++ )
                for (DataSnapshot entrySnap : dataSnapshot.getChildren()) {

                    //  Entry ent = entrySnap.getValue(Entry.class);

                    String[] tempArray;
                    String[] finArray = new String[3];
                    if (!(entrySnap.getValue().toString().equals("navn")) && !entrySnap.getValue().toString().equals("point")) {
                        tempArray = entrySnap.getValue().toString().split("=");

                        finArray[0] = tempArray[1].substring(0, tempArray[1].indexOf(","));
                        finArray[1] = tempArray[2].substring(0, tempArray[2].indexOf(","));
                        finArray[2] = tempArray[3].substring(0, tempArray[3].indexOf("}"));


                        // finArray = tempArray[1].split(",").;
                        Entry tempEnt = new Entry(finArray[0], finArray[2], finArray[1]);
                        entryAList.add(tempEnt);
                    }
                    //  nameList.add(entrySnap.getValue(String.class));


                    if (Strings.isEmptyOrWhitespace(text) && Strings.isEmptyOrWhitespace(text2)) {

                        text = entrySnap.child("name").getValue(String.class);

                        text2 = entrySnap.child("score").getValue(Long.class).toString();

                        nameList.add(text);
                        scorList.add(text2);

                    }

                    for (int i = 0; i < entryAList.size(); i++) {

                        for (int j = i + 1; j < entryAList.size(); j++) {

                            Integer temp1 = Integer.parseInt(entryAList.get(i).getScore());
                            Integer temp2 = Integer.parseInt(entryAList.get(j).getScore());
                            if (temp1 > temp2) {
                                // if (entList.get(i).getScore().compareTo(entList.get(j).getScore()) > 0) {
                                Entry enTemp = entryAList.get(i);
                                entryAList.set(i, entryAList.get(j));
                                entryAList.set(j, enTemp);
                            }
                        }
                    }


                    ScoreAdapter adapter = new ScoreAdapter(getActivity(), entryAList);
                    scoreList.setAdapter(adapter);
                }
                if (!Strings.isEmptyOrWhitespace(nameList.get(0))) {

                    Log.i("data shows data", nameList.get(0));
                    tv10.setText(nameList.get(0));
                    tv10.setVisibility(View.GONE);
                    b12.setVisibility(View.GONE);
                    String tempText = "";

                    adapter = new ScoreAdapter(getActivity(), entryAList);
                    scoreList.setAdapter(adapter);
                    //  tv10.setText(name);
                    //  Log.i("Data was changed", name);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Datacancel", "onCancelled: happened in score activity");
            }
        });


        return superView;
    }


}

