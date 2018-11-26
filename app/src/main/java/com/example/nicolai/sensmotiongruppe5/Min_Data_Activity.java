package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nicolai.sensmotiongruppe5.Fragments.Date_Slider_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Min_Data_frag;

public class Min_Data_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min__data);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.place_holder_date, Date_Slider_frag.newInstance());
        ft.commit();


        FragmentTransaction fb = getSupportFragmentManager().beginTransaction();
        fb.replace(R.id.place_holder_graf, Min_Data_frag.newInstance());
        fb.commit();
    }
}
