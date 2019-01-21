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

import java.util.ArrayList;

public class Score_Activity extends Fragment {


    Highscore_controller hsc;
    Button b12;
    TextView tv10;
    private View superView;
    ArrayList<Entry> entryAList;
    ArrayList<String> nameList, scorList;
    String text, text2;
    ListView scoreList;
    ScoreAdapter adapter;

    /*
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_highscore);



            // getReference("scores"));


    */
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
        scoreList = superView.findViewById(R.id.ScoreList);


        hsc = new Highscore_controller(FirebaseDatabase.getInstance().getReference("scores"));

        b12 = superView.findViewById(R.id.button2);
        tv10 = superView.findViewById(R.id.textView10);

        String message = hsc.getKey(1);
        tv10.setText(message);
        Log.i("message", message);

        adapter = new ScoreAdapter(getActivity(), entryAList );
        scoreList.setAdapter(adapter);

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entry ent = new Entry("Knud",  "23", "3");
                hsc.addScore(ent);
                Log.i("added to database", ent.toString());
                adapter = new ScoreAdapter(getActivity(),entryAList );
                scoreList.setAdapter(adapter);
            }
        });


        hsc.dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("data change detected", " number 1");
                entryAList.clear();
                Entry[] entArray = new Entry[(int) dataSnapshot.getChildrenCount()];
                // for(int i = 0 ; dataSnapshot.getChildrenCount()>i; i++ )
                for (DataSnapshot entrySnap : dataSnapshot.getChildren()) {
                    Log.i("data change detected", dataSnapshot.getChildren().toString());
                    //  Entry ent = entrySnap.getValue(Entry.class);
                    // entryAList.add(entrySnap.getValue(Entry.class));
                    //  nameList.add(entrySnap.getValue(String.class));


                   // long test = entrySnap.child("score").getValue(long.class);

                    //Entry ent = entrySnap.getValue(Entry.class);
//text2 = "hej";
                    if (Strings.isEmptyOrWhitespace(text) && Strings.isEmptyOrWhitespace(text2)) {

                    text = entrySnap.child("name").getValue(String.class);
                    Log.i("found name", "here");
                    text2 =  entrySnap.child("score").getValue(Long.class).toString();
                        Log.i("found number", "et tal");
                    nameList.add(text);
                    scorList.add(text2);

                    }
                    adapter = new ScoreAdapter(getActivity(),entryAList );
                    scoreList.setAdapter(adapter);
                }
                if (!Strings.isEmptyOrWhitespace(nameList.get(0))) {
                    Log.i("for loop ended", " here");
                    Log.i("data shows data", nameList.get(0));
                    tv10.setText(nameList.get(0));
                    tv10.setVisibility(View.VISIBLE);
                    adapter = new ScoreAdapter(getActivity(),entryAList );
                    scoreList.setAdapter(adapter);
                    //  tv10.setText(name);
                    //  Log.i("Data was changed", name);

                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Log.i("Datacancel", "onCancelled: happened in score activity");
            }
        });


        return superView;
    }




}

