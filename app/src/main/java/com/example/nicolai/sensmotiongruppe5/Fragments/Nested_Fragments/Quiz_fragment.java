package com.example.nicolai.sensmotiongruppe5.Fragments.Nested_Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Highscore_controller;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;
import com.example.nicolai.sensmotiongruppe5.Rute.Rute_queue;
import com.example.nicolai.sensmotiongruppe5.Tests.Entry;

import java.util.ArrayList;

public class Quiz_fragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    View view;
    boolean firstGuess = false;
    Integer corrects = 0;
    private IChild_OnFragmentInteractionListener mListener;

    public Quiz_fragment() {
    }

    public static Fragment newInstance(String message, String question, ArrayList choices) {
        Quiz_fragment fragment = new Quiz_fragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_MESSAGE, message);
        args.putString("question", question);
        args.putStringArrayList("choices", choices);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView s = view.findViewById(R.id.question);
        s.setText(getArguments().getString("question"));
        Button c1 = view.findViewById(R.id.choice1);
        Button c2 = view.findViewById(R.id.choice2);
        Button c3 = view.findViewById(R.id.choice3);
        Button c4 = view.findViewById(R.id.choice4);
        ArrayList<String> b = getArguments().getStringArrayList("choices");
        c1.setText(b.get(0));
        c2.setText(b.get(1));
        c3.setText(b.get(2));
        c4.setText(b.get(3));
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstGuess == false) {
                    firstGuess = true;
                } else {
                    Entry ent = new Entry("Jens Hansen", corrects.toString(), "3");
                    Highscore_controller hsc = new Highscore_controller();
                    hsc.createDatabaseReference();
                    //  Highscore_controller hsc = new Highscore_controller();
                    hsc.addScore(ent);
                }
                Rute_queue.getInstance(null).replaceFragment(null, true);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstGuess == false) {
                    firstGuess = true;
                } else {
                    Entry ent = new Entry("Jens Hansen", corrects.toString(), "3");
                    Highscore_controller hsc = new Highscore_controller();
                    hsc.createDatabaseReference();
                    //  Highscore_controller hsc = new Highscore_controller();
                    hsc.addScore(ent);
                }
                Rute_queue.getInstance(null).replaceFragment(null, true);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstGuess == false) {
                    firstGuess = true;
                } else {
                    Entry ent = new Entry("Jens Hansen", corrects.toString(), "3");
                    Highscore_controller hsc = new Highscore_controller();
                    hsc.createDatabaseReference();
                    //  Highscore_controller hsc = new Highscore_controller();
                    hsc.addScore(ent);
                }
                Rute_queue.getInstance(null).replaceFragment(null, true);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corrects += 1;
                if (firstGuess == false) {
                    firstGuess = true;
                } else {
                    Entry ent = new Entry("Jens Hansen", corrects.toString(), "3");
                    Highscore_controller hsc = new Highscore_controller();
                    hsc.createDatabaseReference();
                    //  Highscore_controller hsc = new Highscore_controller();
                    hsc.addScore(ent);
                }
                Rute_queue.getInstance(null).replaceFragment(null, true);

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            return view = inflater.inflate(R.layout.quiz_fragment_layout, container, false);
        }


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChild_OnFragmentInteractionListener) {
            mListener = (IChild_OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {


    }
}
