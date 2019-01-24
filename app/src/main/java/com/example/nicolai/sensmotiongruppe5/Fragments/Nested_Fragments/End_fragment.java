package com.example.nicolai.sensmotiongruppe5.Fragments.Nested_Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Activities.Win_Activity;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;

public class End_fragment extends Fragment implements IChild_OnFragmentInteractionListener {
    private String mParam1;
    private String mParam2;

    private IChild_OnFragmentInteractionListener mListener;

    public End_fragment() {
        // Required empty public constructor
    }

    public static End_fragment newInstance() {
        End_fragment fragment = new End_fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.end_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView s = view.findViewById(R.id.textbox);
        Button next = view.findViewById(R.id.winning);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Win_Activity.class);
                startActivity(i);
            }
        });
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
    public void messageFromChildFragment(Uri uri) {

    }
}