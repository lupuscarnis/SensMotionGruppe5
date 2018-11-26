package com.example.nicolai.sensmotiongruppe5.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.R;


public class Date_Slider_frag extends Fragment {

    public Date_Slider_frag() {

    }

    public static Date_Slider_frag newInstance() {
        Date_Slider_frag fragment = new Date_Slider_frag();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date__slider_frag, container, false);
    }

}
